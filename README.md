![Language](https://img.shields.io/github/languages/top/cortinico/kotlin-android-template?color=blue&logo=kotlin)
![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.karumi/headerrecyclerview/badge.svg) 
# OverScroll
==================

OverScroll is an Android library created to be able to detect overscroll edge inside a ``RecyclerView`` in a easy way. To use this library create a normal ``RecyclerView`` and ``RecyclerView.Adapter``. ⚠️ This library work only with ``LinearLayoutManager.VERTICAL``.

Screenshots
-----------

![Demo Screenshot][1]

Usage
-----

To use ``HeaderRecyclerView`` in your application follow those steps:

* 1 - Add mavenCentral() to repositories block in the gradle file.
* 2 - Add `implementation("com.github.dualbit:overscroll:1.0.")` to the dependencies.
* 3 - Create a normal ``RecyclerView`` and ``RecyclerView.Adapter`` .
* 4 - Call the extension function ``addOverScrollEdge``. Provide the required ``LifecycleCoroutineScope`` parameter and remember not to configure the ``LayoutManager`` instance on your ``RecyclerView``.

```kotlin

    viewBinding.rclMain.addOverScrollEdge(lifecycleScope = lifecycleScope, onTop = ::onTopCallback, onBottom = ::onBottomCallback)

```

* 5 - You can use the ``onTop`` and ``onBottom`` lambda when overscroll the edge from ``RecyclerView``:
```kotlin

    fun onTop() { /*Your code here*/ }
    
    fun onBottom() { /*Your code here*/ }

```

How it works
-----
* A custom ``OnScrollListener`` is attached to the ``RecyclerView``.
* During the scroll is detected when the top/bottom positions are reached based on the first and last elements of the ``LayoutManager`` and the ScrollState.
* When the overscroll event is detected the ``onTop`` or ``onBottom`` events are triggered.

Do you want to contribute?
--------------------------

Please, do it! We'd like to improve this library with your help :)

External resources used in this project
---------------------------------------

* Dualbit.it ©

License
-------
    Copyright 2024 Dualbit

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: ./asset/screenshot_demo_1.gif