def gitUrl = 'git@github.com:nathanagood/cloudformation-examples.git'
def parametersBucket = ''

def cloudFormationJobs = 
[
    [ name: 'exchange-servers', templatePath: 'example-linux-ec2.yml', parametersKeyName: 'example-linux-ec2.parameters' ]
]

cloudFormationJobs.each {
    
    job(it['name']) {
        scm {
            git(gitUrl)
        }
        triggers {
            scm('*/5 * * * *')
        }
        steps {
            shell("echo \"aws cloudformation --stack-name ${it['name']} --template-body file://${it['templatePath']} --parameters s3://${parametersBucket}/${it['parametersKeyName']}\" > ${it['name']}-cmd.txt")
        }
    }
}
