# Deployments 

Instructions for deploying the application in different environments

Application and deployment properties can be configured in both the .env and .yml files located in the corresponding folder

## Build and deploy locally

Build deployment let you deploy the application with the latest changes even before committing them to the repository.
This is done by rebuilding docker images and therefore, we need first to build the project and generate the jar artifact. 

We can use gradle tasks to make our lives easier.

1. ```gradle clean build```
2. ```gradle buildContainersUp```
3. ```gradle createDb``` (or createDbPre to load more data)
4. ```gradle buildContainersDown``` (when finished)

## Deploy locally

Local deployment let you deploy any of the images available in docker hub.

Until a deployment shell script exists, it is possible to deploy using gradle tasks as in the build type

1. ```gradle localContainersUp```
2. ```gradle createDb``` (or createDbPre to load more data)
3. ```gradle localContainersDown``` (when finished)

It is also possible to deploy only the monitoring tools graphite and grafana by using gradle tasks

1. ```gradle grafanaContainersUp```
2. ```gradle grafanaContainersDown```

## Deploy in cloud

TODO: There are plans to include deployment in either:
* GKE
* AWS
* Heroku
* Linode
