# Tunturi ![Stars](https://img.shields.io/github/stars/algalopez/tunturi?style=social)

## Description

Just a small project for 

  * Practising and learning
    * Containers, docker, docker-compose or kubernetes
    * Authentication, authorization, Oauth
    * Automatic builds, CICD, travis, github
    * Design patterns and architectures
    * Other: Aspects, annotations, etc.
    * Monitorization
  * Have some code to start from in future projects

## Project status

Build: 

[![Action status](https://github.com/algalopez/tunturi/workflows/Tunturi%20CICD%20Pipeline/badge.svg)](https://github.com/algalopez/tunturi/actions)

Quality gates: 

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=alert_status)](https://sonarcloud.io/dashboard?id=Tunturi)

<details>
<summary>See full quality gates</summary>

[![Quality Gate Status (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=alert_status)](https://sonarcloud.io/dashboard?id=Tunturi)
[![bugs (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=bugs)](https://sonarcloud.io/component_measures?id=Tunturi&metric=bugs)
[![code_smells (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=code_smells)](https://sonarcloud.io/component_measures?id=Tunturi&metric=code_smells)
[![coverage (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=coverage)](https://sonarcloud.io/component_measures?id=Tunturi&metric=coverage)
[![duplicated_lines_density (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=duplicated_lines_density)](https://sonarcloud.io/component_measures?id=Tunturi&metric=duplicated_lines_density)
[![ncloc (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=ncloc)](https://sonarcloud.io/component_measures?id=Tunturi&metric=ncloc)
[![sqale_rating (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=sqale_rating)](https://sonarcloud.io/component_measures?id=Tunturi&metric=sqale_rating)
[![reliability_rating (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=reliability_rating)](https://sonarcloud.io/component_measures?id=Tunturi&metric=reliability_rating)
[![security_rating (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=security_rating)](https://sonarcloud.io/component_measures?id=Tunturi&metric=security_rating)
[![sqale_index (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=sqale_index)](https://sonarcloud.io/component_measures?id=Tunturi&metric=sqale_index)
[![vulnerabilities (%)](https://sonarcloud.io/api/project_badges/measure?project=Tunturi&metric=vulnerabilities)](https://sonarcloud.io/component_measures?id=Tunturi&metric=vulnerabilities)

</details>

Docker

|                                                                             |                                                                       |                                                                |                                                                                |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------- | -------------------------------------------------------------- | ------------------------------------------------------------------------------ |
|[Tunturi-app](https://hub.docker.com/repository/docker/algalopez/tunturi-app)| ![](https://img.shields.io/docker/v/algalopez/tunturi-app?color=blue) | ![](https://img.shields.io/docker/pulls/algalopez/tunturi-app) | ![](https://img.shields.io/docker/image-size/algalopez/tunturi-app?color=blue) |
|[Tunturi-db](https://hub.docker.com/repository/docker/algalopez/tunturi-db)  | ![](https://img.shields.io/docker/v/algalopez/tunturi-db?color=blue)  | ![](https://img.shields.io/docker/pulls/algalopez/tunturi-db)  | ![](https://img.shields.io/docker/image-size/algalopez/tunturi-db?color=blue)  |

Deployment (Not implemented yet): 

[![Deployments](https://img.shields.io/github/deployments/algalopez/tunturi/tunturi)](https://github.com/algalopez/Tunturi/deployments)

Progress

[![Tag SemVer](https://img.shields.io/github/v/tag/algalopez/tunturi?color=yellow)](https://github.com/algalopez/Tunturi/releases)
[![Milestone progress](https://img.shields.io/github/milestones/progress/algalopez/tunturi/1?label=milestone%201.0.0&color=yellow)](https://github.com/algalopez/Tunturi/milestones)

<details>
<summary>See full progress</summary>

[![Tag SemVer](https://img.shields.io/github/v/tag/algalopez/tunturi?color=yellow)](https://github.com/algalopez/Tunturi/releases)
[![Milestone progress](https://img.shields.io/github/milestones/progress/algalopez/tunturi/1?label=milestone%201.0.0&color=yellow)](https://github.com/algalopez/Tunturi/milestones)

[![Open issues](https://img.shields.io/github/issues-raw/algalopez/tunturi?color=yellow)](https://github.com/algalopez/Tunturi/issues)
[![Closed issues](https://img.shields.io/github/issues-closed-raw/algalopez/tunturi?color=yellow)](https://github.com/algalopez/Tunturi/issues?q=is%3Aissue+is%3Aclosed)
[![Open PR](https://img.shields.io/github/issues-pr-raw/algalopez/tunturi?color=yellow)](https://github.com/algalopez/Tunturi/pulls)

[![Commits since tag](https://img.shields.io/github/commits-since/algalopez/tunturi/1.0.0/develop?color=yellow)](https://github.com/algalopez/Tunturi/commits/develop)
[![Commit activity](https://img.shields.io/github/commit-activity/y/algalopez/tunturi?color=yellow)](https://github.com/algalopez/Tunturi/commits/develop)
[![Contributors](https://img.shields.io/github/contributors/algalopez/tunturi?color=yellow)](https://github.com/algalopez/Tunturi/graphs/contributors)

</details>

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
