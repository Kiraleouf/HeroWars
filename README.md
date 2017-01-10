# HeroWars Template

This project means differents practices arround RX, Realm, Dagger and UI pattern.

## Run project

To run project,  clone it and execute this command: 

’’’bash
git clone *****
./gradlew assemble
’’’

Last command build an APK in release and debug state.

To run tests:

’’’bash
./gradlew build
’’’
## Git workflow

Project has two main branches:

- 'master' is the main branch of release app versions. The last commit 
is the last release version. 
- 'develop' is the development branch of project on which the developpers contributs.

To identify release version on GIT we used this TAG format : v1.YY.MM.XX, 
Example : v1.16.11.01 

## Merge Request

All contributions are made in seperate branches are merged in the 'develop" branch 
via merges requests.

## TODO List

- Documentation
- Gradle configuration (release, debug, app name, apk name, etc.)
- Setup Circle CI
- Refactor package (ui, network, repo)
- Improve Dagger implementation (arround constructors)
- Write web services with swagger
- Implement retrofit mock
- Implement network manager which return observable and callback retrofit 
- Implement network unit tests
...
