[![Build Status](https://travis-ci.com/zzm824/Travis_CI_Tutorial.svg?branch=main)](https://travis-ci.com/zzm824/Travis_CI_Tutorial)

# How to Use Travis CI with Java on GitHub
This repository is a demo of how to use [Travis CI](https://docs.travis-ci.com/) in a Java project on GitHub.

# Tutorial
## 1. Build
* Sign in to your user Github account and fork this repo
* Go to [Travis-ci.com](https://travis-ci.com/) and Sign in with GitHub account

![avatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210529151710.png)

* Accept the authorization of Travis CI
* Click on your profile picture in the top right of your Travis Dashboard, click _Settings_

![avator](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210529153038.png)

* Select the repo you want to use with Travis CI. If repo cannot be found, click _Sync account_


![avatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210529153337.png)

* Back to Github. Create a _.travis.yml_ file and add the following content(to tell Travis CI what to do). Commit and push the changes to the root directory of your repo
```
# Building a Java project
language: java

# We can specify a list of JDKs to be used for testing
jdk:
 - openjdk11
```

* Back to Travis CI. Click your repo and you will find it start build by Travis CI

![avatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210529152709.png)

* If the build is passed, you will see as follows.

![avatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210529154131.png)

* Create the README.md badge(copy the markdown code from [Travis CI](https://travis-ci.com/) and paste in the top line of _readme.md_)
> [Travis CI](https://travis-ci.com/) => select your repository => click the build status image => select _FORMAT_ as Markdown => Copy the code of _RESULT_ => paste in the top line of _readme.md_

![acatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210529154809.png)

* You will see a badge like this in your repo of Github

![acatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210530100304.png)

## 2. Test
This repo also integrates with Codecov to generate reports.
* Go to the [Codecov website](https://about.codecov.io/) and Sign in with GitHub account.

![avatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210530091144.png)

* Accept the authorization of Codecov and add your repository 
* Add the following code in the end of _.travis.yml_ file
```
after_success:
  - bash <(curl -s https://codecov.io/bash)
```
* Commit and push the changes to your repo, which will trigger a new build in Travis CI. This is to enable CodeCov's coverage.
If a build is successful, the code is submitted for coverage analysis
* Create the README.md badge of coverage
> [Codecov](https://codecov.io/gh)  =>select your repository => Setting => Bedge => Copy the contect of Markdown => paste in the second line of readme.md

* You will see the badge like this

![acatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210530100532.png)

## 3.Deploy
* Travis CI can help you release to Github after a success build. You will need to provide a Github personal access token(Tutorial: https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token). 
* Now that we have a token go to the project you want to use it with on Travis CI. Then on your project page, go to More options => Settings.

![avatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210530094300.png)

* In the _Environment Variables_ section, add variable named _GITHUB_TOKEN_ and use the generated token from Github in previous step.

![avatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/env-var.png)

* Travis CI can automatically upload assets to git tags on your GitHub repo. So you need create a tag in a GitHub repo(Tutorial:  https://stackoverflow.com/questions/18216991/create-a-tag-in-a-github-repository)
* Add the following to your  _.travis.yml_(For more information about parameter configuration, please read https://docs.travis-ci.com/user/deployment-v2/providers/releases/)

```
deploy:
  provider: releases
  file_glob: true
  skip_cleanup: true
  api_key: $GITHUB_TOKEN
  file:
    - .travis.yml
  on:
    tags: true
    branch: tag
    repo: <your_github_username>/<your_repo_name>

```
* Commit and push to trigger a Travis CI build. If the build is successful, you will see _.travis.yml_ in the _Releases_ section of your repo like this

![avatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210530104406.png)

## 4. Trigger a failed build and notify build result

 * Travis CI can notify you about your build results through email, IRC, chat or custom webhooks (https://docs.travis-ci.com/user/notifications/)
 *  Add the following to your  _.travis.yml_, which will send the build result to your Email
 ```
 notifications:
  email:
    - one@example.com(replace by your e-mail)
 ```
 * Modify line 61 of trityp.java(src/main/.../trityp.java)  as follows
 ```
 triOut = triOut + 2;
 ```
![acatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210530114344.png)
 
* Commit and push to trigger a Travis CI build. As expected, this build will failed and you will receive an Email like this

![acatar](https://github.com/kreattang/TravisCI_Java/blob/main/img/20210530113941.jpg)

