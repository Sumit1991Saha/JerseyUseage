Steps to build and deploy to tomcat :-
1. Normal mode :- ./buildScript.sh -n
2. Debug mode :-  ./buildScript.sh -d   (Tomcat's debug port is 8000)

Simple response :- localhost:8180/JerseyUseage/webapi/myresource
XML Response :- localhost:8180/JerseyUseage/webapi/xml/activities
JSON Response :- localhost:8180/JerseyUseage/webapi/json/activities

For the json support in this project, following lib is required
org.glassfish.jersey.media.jersey-media-json-binding

Important points :-
1. If expecting an response in XML format then @XmlRootElement annotation over the model objects is mandatory.
2. If expecting an response in JSON format then @XmlRootElement annotation over the model objects is not mandatory.


Resources :- 
1. @POST   - Create 
            :- http://localhost:8180/JerseyUseage/webapi/json/activities
2. @GET    - Get All Activities 
            :- http://localhost:8180/JerseyUseage/webapi/json/activities
3. @GET    - Get Activity by id 
            :- http://localhost:8180/JerseyUseage/webapi/json/activities/{activityId}
4. @PUT    - Update an Activity 
            :- http://localhost:8180/JerseyUseage/webapi/json/activities/{activityId}
5. @DELETE - Delete an Activity 
            :- http://localhost:8180/JerseyUseage/webapi/json/activities/{activityId}
6. @GET    - Searching using description (query param) 
            :- http://localhost:8180/JerseyUseage/webapi/json/search/activities/v1?description=?&description=?
7. @GET    - Searching using description and duration (query param) 
            :- http://localhost:8180/JerseyUseage/webapi/json/search/activities/v2?description=?&description=?&durationFrom=?&durationTo=?
8. @POST   - Searching using search object (minimum change required in this workflow if search is performed this way) 
            :- http://localhost:8180/JerseyUseage/webapi/json/search/activities/v3




