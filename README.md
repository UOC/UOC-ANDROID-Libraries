UOC-ANDROID-Libraries
=====================

* [Introduction](#introduction)
* [Description](#description)
* [How To Install](#how-to-install)
* [More Information](#more-information)

## Introduction

This Android library contains the login functions for UOC Campus and the calls to the OpenAPI.

All code on this repository has been made with android studio.

## Description

Esquelet contains all the source code of the library plus a login module for android and some examples of how to use the library.

Skeleton contains the same examples as Esquelet, an old login for android and has the library included as .jar files.

The examples of Esquelet and Skeleton are representative of all the different kinds of elements that the api returns. The 4 examples explaining how to use some of the functions are: 

* /user Get
* /mail/messages Get
* /calendar/events Post
* /mobileresources Get

On "openapi_jars" you will find the .jar files of the library divided by objects (boards, subjects, etc). Each .jar contains all functions that return the object or a list of that object.  All .jar files need "openapi-core-0.1.jar" to work. Core contains an abstract constant class, a login interface, the AuthObj class and a class needed to create different calls to the api.

## How To Install

Both in Esquelet and in Skeleton you can see how to use the library only using .jar files (Skeleton) or using the source code directly (Esquelet).

The only thing needed to use the library is to add the correct maven dependencies in your gradle files and the needed permissions in your manifest.

* Dependency
On the build.gradle of your proyect you need to add: 
"classpath 'org.robolectric:robolectric-gradle-plugin:0.11.+'"

## Dependency

## More Information

You can find more information in our [blog][OpenApi].

[OpenApi]: http://open-api.uoc.edu/documentacio/uoc-public-api/
