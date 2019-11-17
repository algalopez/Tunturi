# Tunturi

## Description

Just a small project for 

  * Practising and learning
    * Containers, docker, docker-compose or kubernetes
    * Authentication, authorization, Oauth
    * Automatic builds, jenkins, travis
    * Design patterns and architectures
    * Other: Aspects, annotations, etc.
  * Have some code to start from in future projects

## Status

Project Status

[![Build Status](https://travis-ci.org/algalopez/Tunturi.svg?branch=develop)](https://travis-ci.org/algalopez/Tunturi)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=alert_status)](https://sonarcloud.io/dashboard?id=Tunturi)

Project Status Details

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

* **database:** Database. Soon to be refactored to flyway
    * DDL: Database structure
    * DML: Some data for Type tables / Tests / Environments
* **deploy:** Useful things for a manual deployment in production
    * artifact: Application jar
    * configuration: Example of application properties and other configuration files
* **docker:** Containers for deployment
    * app: Application dockers for different environments
    * db: Database dockers for different environments
    * scripts: Some useful scripts
* **pipeline:** Unmaintained CICD pipelines due to some cloud hosted tools (Travis) forcing pipelines to be in particular folders
* **src:** Application code and unit and integration tests
    * auth: Spring security classes for authorization and authentication
    * common: Cross-cutting classes
    * config: Spring configuration classes
    * echo: Echo server
    * notification: ---
    * user: User information
    