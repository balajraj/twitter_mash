# The github twitter command line api mash project.

### Introduction
The GitHubTwitterDriver has the main method which initiates the whole flow for this project.The driver reads a config file from the env variable config_file. So before running the project using sbt run  please set the below environment variable as shown as follows assuming that you are using a mac or linux environment to run the code.

```sh
export config_file=src/main/resources/config.properties
```

### Config file
 The config.properties has many variables out of which I have explained the ones are of interest.
 - consumer_key the twitter api key 
 - consumer_secret the twitter api secret key
 - number_of_request will decide how many of the twitter request will be send out. According to the project it is set to default of 10, owing to twitter api query limit.
 - request_from  the variable used in the twitter query which will decided how far back you want to go to query the tweets
 - output_file  is the location where the output file will be written.

### Running the project
   Once you have set the above configuration variables you can run the code by typing sbt run and the output will be written to the output file, and please hit ctrl-c to exit out of sbt console after output generation. The output response is also printed in the console and will look like the below.

```sh
{"result_tweets":[{"repo":"component/reactive","tweets":[]},{"repo":"reactivemanifesto/reactivemanifesto","tweets":[]},{"repo":"ReactiveCocoa/ReactiveCocoa","tweets":[{"date":"Wed Jul 12 11:14:59 +0000 2017","text":"Release notes from ReactiveCocoa : 6.0.0 Release Candidate 3 https://t.co/n4bsIDY6tA\n\nThis is the third release candidate of ReactiveCocoa…","person":"github-release-feeds","location":""}]},{"repo":"ReactiveX/RxPY","tweets":[]},{"repo":"rpominov/kefir","tweets":[]},{"repo":"playframework/playframework","tweets":[{"date":"Sun Jul 16 12:48:55 +0000 2017","text":"https://t.co/5CBQcuP4ru play2.6.1でdevmodeでうんともすんとも言わなくなるやつprがマージされてる！","person":"Nakamura Masato","location":"Japan"},{"date":"Fri Jul 14 12:43:09 +0000 2017","text":"https://t.co/5CBQcuP4ru dev modeの修正案見てて、アッこれはdeadlockするわなっ>て思った。comitter sanたちあんまりdevモードさわってないのかねぇ","person":"Nakamura Masato","location":"Japan"},{"date":"Fri Jul 14 12:41:29 +0000 2017","text":"https://t.co/m8q36leXKY https://t.co/5CBQcuP4ru play 2.6.1でdevモードで動かすとうんともすんとも言わなくなる
の不具合みたい。","person":"Nakamura Masato","location":"Japan"},{"date":"Fri Jul 14 08:51:03 +0000 2017","text":"RT @LeszekGruchala: Dear people, don’t use Play 2.6.1 (2.6.0 is fine) There is deadlock happening in dev mode https://t.co/y69M9Fcxfs #play…","person":"Jakub Kozłowski","location":" Wrocław, Poland"},{"date":"Fri Jul 14 08:26:31 +0000 2017","text":"Dear people, don’t use Play 2.6.1 (2.6.0 is fine) There is deadlock happening in dev mode https://t.co/y69M9Fcxfs #playframework","person":"Leszek Gruchała","location":"Szczecin, Poland"}]},{"repo":"paypal/squbs","tweets":[]}]}
```
