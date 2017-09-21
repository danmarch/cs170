import os
import boto3

client = boto3.client('codedeploy')

response = client.create_application(
    applicationName='cat_man'
)

try_it = client.create_deployment(
    applicationName='cat_man',
    deploymentGroupName='try_it_out',
    revision={
        'revisionType': 'GitHub',
        'gitHubLocation': {
            'repository': 'string',
            'commitId': 'string'
        }
    }
)

try_it = client.create_deployment()
