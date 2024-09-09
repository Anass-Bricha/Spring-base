# Spring-base project
A basic spring base project for simple projects containting the implementation of spring security 6 with the latest changes, along with basic dependencies such as h2 and spring web.
## Authentication & authorization : 
- The authentication implementation is stateless using jwt with a default expiring date of one minute.
- The authorization is based on authorities (Not roles) .

## Login Api :
- The login api is very simple, it takes as a body usernme & password and returns a jwt token.
- No refresh token is implemented
