# distributed-computing

This project has two sections which represents few aspects of Distributed Computing. Here is some information about these sections:

## 1.  Clustering

This section covers Clustering (Horizontal scaling) aspect of Distributed Computing which demenostrates: 

  1. **Scalability**: Nginx distributes load between Tomcat instances and more Tomcat instances could be added if required.
  2. **Availability:** Since there are two tomcat nodes, avialability is high.
  3. **Fault Tolerance:** If one Tomcat node fails, the other takes over as if nothing happened (session replication is turned on).

In this section, a two node Tomcat 9 cluster is setup (refer [Apache Tomcat 9 docs](https://tomcat.apache.org/tomcat-9.0-doc/cluster-howto.html)) with Nginx as Load Balancer and a sample Java Web application (disctibuted-computing-app) is deployed on both the Tomcat nodes. The sample application exposes few REST APIs (Swagger UI is available) to create/destroy Http session. All the configuration and source code in provided as part of "clustering" github directory above. The Nginx is configured in sticky session (using JSESSIONID) mode.

There are two server.xml files provided for each Tomcat node. This is because Tomcat supports two Clustering configurations: Delta and Backup (both are covered by this section). Delta config is suitable for small clusters and Backup config is suitable for large clusters. 

**Use Case:** Once the setup is ready, perform the following steps:

  1.  Hit the create session API of the load balanced Nginx url which will create a session on one of the Tomcat 9 Node (check which Node using logs).
  2.  Now, hit the create session API again (due to sticky session Nginx config it will go to the same Tomcat node) and you will see a message saying "Session already exists".
  3.  Stop the Tomcat 9 Node on which the session was created.
  4.  Now, hit the create session API again, you will still see the same message "Session already exists" even though the Nginx routed the request to the other Tomcat node this time.
  5.  This happens because Tomcat cluster session replication if turned on and all the session data is presevered (on the other node) even though one of the Tomcat node crashed/stopped. 

The setup steps are not provided due to unavailability of time.

## 2. Computing

The details are given in the corresponding section above. There is no prototype/sample provided in this section as the explanation is sufficient for understanding purpose.


