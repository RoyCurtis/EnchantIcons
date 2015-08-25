[![Build Status](http://ci.haun.guru/buildStatus/icon?job=EnchantIcons)](http://ci.haun.guru/job/EnchantIcons/)

**EnchantIcons** is a client-only mod that makes enchanted books easier to find, based on their inner enchantment. It is available for 1.7.10 and 1.6.4.

This is the 1.7.10 branch, `master`.

# Building

## Requirements

* [Gradle installation with gradle binary in PATH](http://www.gradle.org/installation). Unlike the source package provided by Forge, this repository does not include a gradle wrapper or distribution.

## Usage
Simply execute `gradle setupCIWorkspace` in the root directory of this repository. Then execute `gradle build`. If subsequent builds cause problems, do `gradle clean`.