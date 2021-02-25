# #AndroidDevChallenge Week 1

![Workflow result](https://github.com/Jorkoh/compose-challenge-week1/workflows/Check/badge.svg)


## :scroll: Description
A simple cat adoption app for the #AndroidDevChallenge week 1. Contains an overview screen that displays a list of cats, and a detail screen showing each cat's details. To test it you will need to add an API token from [TheCatApi](https://docs.thecatapi.com/) to gradle.properties like this `CAT_API_TOKEN=token_goes_here`


## :bulb: Motivation and Context
The challenge itself is pretty straightforward. Decided to include some basic app structure with Hilt, flows, Room caching, view models... Also used Retrofit to get random cat images from [TheCatApi](https://docs.thecatapi.com/) and [Coil](https://github.com/chrisbanes/accompanist/tree/main/coil) to load them.


## :camera_flash: Screenshots
<!-- You can add more screenshots here if you like -->
<img src="/results/screenshot_1.png" width="260">&emsp;<img src="/results/screenshot_2.png" width="260">

## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```