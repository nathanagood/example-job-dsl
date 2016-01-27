def gitUrl = 'git@github.com:nathanagood/chef-repo.git'

job('Sample Job 1') {
    scm {
        git(gitUrl)
    }
    triggers {
        scm('*/15 * * * *')
    }
    steps {
        shell('echo \"Hello, world\" > helloworld.txt')
    }
}
