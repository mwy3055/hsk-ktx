[![](https://jitpack.io/v/mwy3055/hsk-ktx.svg)](https://jitpack.io/#mwy3055/hsk-ktx)

# hsk-ktx
Kotlin extension library for you and me!

## Download (Gradle)
1. Add this code in your root-level ``build.gradle``:
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

2. Then add this code in dependency:
```
dependencies {
        implementation 'com.github.mwy3055:hsk-ktx:<version-name>'
}
```

## Features
* Provides some useful methods that extend Kotlin core features
  * e.g. ``Collections<T>.distinctRandoms(count: Int)``
* Now extends ``Collections``, ``Numbers``, ``String``, [``Gson``](https://github.com/google/gson)
* Assertion methods for ``Flow<T>``

## License
```
Copyright 2022 hsk-lab.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
See full license [here](https://github.com/mwy3055/hsk-ktx/blob/main/LICENSE).
