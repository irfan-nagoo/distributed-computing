# computing

![dc](https://github.com/raptor-23/distributed-computing/assets/142492599/308105cc-900a-43b0-ba52-37d33776007c)


## Intoduction

Distributed computing refers to distributing the processing ability of a subject over a range of hosts in order to achieve scalability, availability, performance etc. In terms of web application development, distributed computing means decomposing a business domain into various sub domains and then translating those sub-domains into actual functional components e.g. Micro frontend apps, Microservices, database modeling, network components etc. These loosely coupled components perform a slice of the main task in parallel or serial fashion and contribute to the distributed computing. 

## Micro Frontend Applications

The diagram above demonstrates a typical Microservice architecture (consisting of major components only) which is a good example of distributed computing. If we have a complex frontend UI then that could be decomposed into a collection of Micro frontend applications (shown in dotted lines) which would scale individually and provide all features of Microservice paradigm. Each of these micro frontend applications could represent a particular section in a web page e.g. headers/footer, navigation side bar, content section etc. As shown in the diagram, the Micro frontend applications could be developed in any technologies e.g. Angular, Reactjs, Html etc. and could be owned by different teams. The Micro frontend application are generally preceded by a container layer (could be a micro frontend app) which actually faces the client, makes requests on behalf of the client to individual Micro frontend application to fetch the content, integrates it and then returns integrated response to the client. This layer can also include the authentication (like SSO) logic. This integration approach is generally recommend due to various advantages. For less complex applications, a single front end monolithic application could also suffice.

## Backend Microservices

The backend Microservice layer could be implemented in variety of languages like:
  1. Java (SpringBoot, Quarkus etc.),
  2. Go (GoMicro GoKit),
  3. NodeJS
  4. Python (Flask, Django)
  5. C# etc.

This layer performs major contribution to distributed computing. As shown in the diagram, there are lot of other components like distributed caches, search solutions, messaging brokers, streaming platforms etc. which are an extension to the Microservices platform.

## Databases

The database layer majorly contributes to the data modeling, data storage, query execution etc. and has a significant contribution to distributed computing. The relational as well NOSQL database technologies could be used as per requirement of the use case.  


