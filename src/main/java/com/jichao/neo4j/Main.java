package com.jichao.neo4j;

import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;


//https://neo4j.com/developer/java/
public class Main {

    private static Node getNodeById(Transaction tx, Long id) {
        String queryStr = "MATCH (c) WHERE ID(c) = {id} RETURN c";
        StatementResult r = tx.run(queryStr, parameters("id", id));
        if (r.hasNext()) {
            return r.next().get("c").asNode();
        } else {
            throw new IllegalStateException("Not found is");
        }
    }
    public static void main(String[] args) {
        String uri = "bolt://localhost:7687";
        String user = "";
        String password = "";
        Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));

        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                String queryStr = "MATCH p=(my_city:City)-[:KNOWS*1..2]-(friend_city) WHERE my_city.name = \"shanghai\"  RETURN my_city,friend_city,relationships(p)";
                StatementResult result = tx.run(queryStr);
                while (result.hasNext()) {
                    Record r = result.next();
                    System.out.println(r.get("friend_city").asNode().get("name").asString());
                    List<Relationship> rels = r.get("relationships(p)").asList(Values.ofRelationship());
                    for(Relationship rel: rels) {
                        String startNodeName = getNodeById(tx, rel.startNodeId()).get("name").asString();
                        String endNodeName = getNodeById(tx, rel.endNodeId()).get("name").asString();
                        System.out.println("\t" + startNodeName + " -> " + endNodeName);
                    }
                }
                tx.success();
            }

        }
    }
}
