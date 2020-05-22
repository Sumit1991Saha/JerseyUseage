Execute the buildScript.sh to build the maven project, copy paste the war file to tomcat's webapps location.
And then restart the tomcat server.

Simple response :- localhost:8180/JerseyUseage/webapi/myresource
XML Response :- localhost:8180/JerseyUseage/webapi/xml/activities
JSON Response :- localhost:8180/JerseyUseage/webapi/json/activities

For the json support in this project, following lib is required
org.glassfish.jersey.media.jersey-media-json-binding

Important points :-
1. If expecting an response in XML format then @XmlRootElement annotation over the model objects is mandatory.
2. If expecting an response in JSON format then @XmlRootElement annotation over the model objects is not mandatory.


Resources :- 
1. Create @Post :- localhost:8180/JerseyUseage/webapi/json/activities
2. Get All Activities @Get :- localhost:8180/JerseyUseage/webapi/json/activities
3. Get Activity by id :- localhost:8180/JerseyUseage/webapi/json/activities/{activityId}
3. Update an Activity :- localhost:8180/JerseyUseage/webapi/json/activities/{activityId}


