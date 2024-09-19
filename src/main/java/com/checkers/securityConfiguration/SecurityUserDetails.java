package com.checkers.securityConfiguration;

public interface SecurityUserDetails {
    String getUsername();

    String getPassword();

    String getRoles();
}

//how this works
//Automatic Mapping: Spring uses reflection and proxy-based mechanisms to dynamically create an object that implements
// the UserLoginProjection interface at runtime. It does the following:
//
//Retrieves the values for username and password from the query result.
//Automatically maps these values to the getUsername() and getPassword() methods in the projection.
//
//No Fields Needed in the Interface: Because Spring handles this behind the scenes, there’s no need for you to define
// variables like username or password in the interface. Spring generates a proxy class at runtime that has the methods
// (getUsername() and getPassword()) but holds the actual values returned from the database.