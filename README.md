UOC-ANDROID-Libraries
=====================

* [Introduction](#introduction)
* [How To Use](#how-to-use)
* [More Information](#more-information)

## Introduction

This Android library contains the login functions for UOC Campus and the calls to the OpenAPI.

The library also contains 4 examples explaining how to use some of the functions. Those functions are: 

* /user Get
* /mail/messages Get
* /calendar/events Post
* /mobileresources Get


## How To Use

If you want to run the example code, just download the project "Esquelet" from this repository, open it 
with Android Studio and run it.

If you want to use our library, the fastest way to include it in your project, at least with Android Studio, 
is to download "OpenAPILibrary" from this repository, create a new Android library module with the same name
in your desired project and then replace the new module folder with the one from this repository. It's important
that the main activity of your project extends "LoginActivity" and to have implemented the call:

"@Override
    public Intent NextActivityIntent() {
        return new Intent (this, first_activity_of_your_project.class);
    }"

## More Information

You can find more information in our [blog][OpenApi].

[OpenApi]: http://open-api.uoc.edu/documentacio/uoc-public-api/
