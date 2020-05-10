# Tunturi

## Description

Just a small project for 

  * Practising and learning
    * Containers, docker, docker-compose or kubernetes
    * Authentication, authorization, Oauth
    * Automatic builds, CICD, travis
    * Design patterns and architectures
    * Other: Aspects, annotations, etc.
    * Monitorization
  * Have some code to start from in future projects

## Status

Build Status

[![Build Status](https://travis-ci.org/algalopez/Tunturi.svg?branch=develop)](https://travis-ci.org/algalopez/Tunturi)

Quality gates

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=alert_status)](https://sonarcloud.io/dashboard?id=Tunturi)

[![bugs (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=bugs)](https://sonarcloud.io/component_measures?id=Tunturi&metric=bugs)
[![code_smells (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=code_smells)](https://sonarcloud.io/component_measures?id=Tunturi&metric=code_smells)
[![coverage (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=coverage)](https://sonarcloud.io/component_measures?id=Tunturi&metric=coverage)
[![duplicated_lines_density (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=duplicated_lines_density)](https://sonarcloud.io/component_measures?id=Tunturi&metric=duplicated_lines_density)
[![ncloc (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=ncloc)](https://sonarcloud.io/component_measures?id=Tunturi&metric=ncloc)
[![sqale_rating (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=sqale_rating)](https://sonarcloud.io/component_measures?id=Tunturi&metric=sqale_rating)
[![alert_status (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=alert_status)](https://sonarcloud.io/component_measures?id=Tunturi&metric=alert_status)
[![reliability_rating (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=reliability_rating)](https://sonarcloud.io/component_measures?id=Tunturi&metric=reliability_rating)
[![security_rating (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=security_rating)](https://sonarcloud.io/component_measures?id=Tunturi&metric=security_rating)
[![sqale_index (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=sqale_index)](https://sonarcloud.io/component_measures?id=Tunturi&metric=sqale_index)
[![vulnerabilities (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=vulnerabilities)](https://sonarcloud.io/component_measures?id=Tunturi&metric=vulnerabilities)

## Project Folder Structure

* **database:** Database scripts for flyway migrations
    * migration: Migration scripts
    * pre: Data for pre environment
* **artifact:** Generated artifacts useful for docker builds and deployments
    * bin: Microservice jar
    * configuration: Examples of configuration files 
* **deploy:** Scripts for docker deployment in different environments 
    * build: Deploy application locally by rebuilding docker images with the latest artifacts 
    * local: Deploy application locally using DockerHub images
* **docker:** DockerFiles and script for building containers
    * app: App dockerfile
    * db: Db dockerfile
    * script: Some useful scripts
* **src:** Application code and unit and integration tests
    * auth: Spring security classes for authorization and authentication
    * common: Cross-cutting classes
    * config: Spring configuration classes
    * echo: Echo server
    * notification: ---
    * user: User information
* **log:** Application logs
* **doc:** Application information and usage examples

## Deployments

[Link to deployment readme](deploy/README.md)

## Playing with the app

[Link to doc readme](doc/README.md)
