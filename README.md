#An in-house navigation system


## Technical Docuumentation

[[_TOC_]]

---

:scroll: **START**

### Introduction

This is an In-House-Navigation system between Base Station and Mobile Station.


---

### Technical specification
There are X (x < 100) Base stations in the system and Y (y < 100) Mobile stations in the system.
Base stations can detect the presence of mobile stations in a certain radius
(detectionRadiusInMeters). When detected, MS id and timestamp are reported by Base Station via a RestEndpoint1. One MS can be reported multiple times by multiple Base Stations

Mobile Station position  can also be queried from RestEndpoint2

The system's data is persisted in an in-memory datastore.


### This service is built with the following

- Spring Boot Framework v3.0.5
- H2 in-memory database
- The Program is built using a microservice comprising of two services (base station service) and (mobile station service) communicating with each other.
- A database data is run and records are populated automatically upon launch of the program.
- The program documentation is done using OpenAPI and Spring Doc
---


---

### Steps to Build and run

- Download project directory
- Navigate to project root/module containing the services
- Launch the program in the following order: Discovery-Service -> Base-Station-Service -> Mobile-Station-Service

- The services Endpoint can be tested via swagger UI:
    base-station-service: http://localhost:8081/swagger-ui/index.html#
    mobile-station-service: http://localhost:8082/swagger-ui/index.html#


---

### Accessing the Service Endpoints
- The services Endpoint can be tested via swagger UI:
    base-station-service: http://localhost:8081/swagger-ui/index.html#
    mobile-station-service: http://localhost:8082/swagger-ui/index.html#
    



:scroll: **END**
