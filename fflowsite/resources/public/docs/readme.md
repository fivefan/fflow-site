# QUICK START

## Introduction
A modern, powerful editor for building FramerJs powered prototype.
FramerFlow, desktop application for Windows, provides great code and design features as followings:

* Framer.js fully integrated
* Built-in supports regular JavaScript, ES6 and FlowType flavored syntax
* Module system (CommonJs style)
* Built-in console log/ REPL system
* Intellisense powered by awesome [FlowType](https://flowtype.org)
* Layer inspection tools
* Inline watch
* Etc.

## Getting ready
FramerFlow is a powerful editor for building a beautiful prototype which is
powered by FramerJs. It comes with built-in support for JavaScript, ES6 and FlowType flavored
syntax(CoffeeScript is not supported in the current build).

### Install
Click following link and download packaging file. After downloading just run it.

[DOWNLOAD SETUP >](http://www.google.com)

> *CAUTION*
>
> **Windows platform** is only available. This is beta build so do it at your own risk.
>



If you do not want to install or installation causes some problems,
here is zip link.  Download it and unzip somewhere.
Simply run `framerflow.exe`.

[DOWNLOAD ZIP >](https://www.google.com)


After successful install you should be able to see the initial screen.

### Release Notes
You can check the release notes at [here](http://www.google.com).


# USER GUIDE

## Start a new project

Starting a new project is simple and easy.

1. Launch FramerFlow appliaction.
2. Click File > New Project on the application menu.
3. Enter project name and folder location in the project set-up dialog.
4. Click OK.
5. You can see very basic skeleton project code.

<div>
  video goes at here.
</div>

### Notes on generated code

The generated code looks somewhat different what you usually see with
FramerStudio. FramerFlow uses **JavaScript** as main language for coding task.
Actually the language is ES6 JavaScript with additional type annotation which
is from FlowType.

```javascript
/* @flow */
```
First line starts with very strange and special comment. The comment is
a sort of hint for FlowType system. FramerFlow heavily rely on flowtype
for its **intellisense** and **static analysis**. When you type some
keystrokes, the recomended list is almost from FlowType system. So the
first line is a critical to get good intellisense. The same rule is applied
when you are creating a module file. __Please put this very special comment
in any files you're using except npm modules.__


```javascript
require("./framerflow.cfg");
```
`framerflow.cfg` is auto generated file and has configurations for project
such as device, background color and rotation and etc. The `require` call
brings those configuration into a live.

> Whenever you change device or background via FramerFlow, the file is
> automatically generated.

That's it. Other parts are plain JavaScript code.


## Coding tools
FramerFlow has decent coding features to help you better.

* ES6 with type annotation
* Modules
* Instant Watches
* REPL and Console
* Flowtype's Static Analysis

### ES6 with type annotation
ES6 is next version of JavaScript. Its expressive power is same with CoffeeScript
in terms of simplicity, beauty. By adopting ES6 you can keep similar things
when you do CoffeeScript codings.

* `()=>{}` - Fat arrow possible for simple inline function or anonymouse function
* `let` - Scoped variable definition.
* `const` - Read-only variable. Great to simply magic number or for immutable things.
*






### Modules
Module is a basic building unit for complex, sophiscated project. If your problem is
too big to solve at once, split it into smaller problems until you get a clear picture
for solutions.

FramerFlow basically supports CommonJs module. `require` is used to import module file
into your project. If you use `npm` module, you can simply include by calling `require(module_name)`

```javascript
require("lodash");
```
If you have your own project's module, it should be included by using relative path
against project root path.

```javascript
require("./YOUR_OWN_MODULES/MODULE_NAME.JS");
```

FramerStudio based project has modules under `modules` directory. In order to include
those module, you should provide relative path to `require` function.

```javascript
require("./modules/AwesomeFramerJsModule.js");
```

Module feature can be used to import asset definitions. Imported assets are saved under
`imported` folder. When you import Photoshop PSD file, the generated images and layer
definitions files are placed altogether. And also FramerFlow will generate simple module
file which can be imported into your project so layer name can be tracked by intellisense
feature.

```javascript
require("./imported/ImportedAsset/layers.js");
```

When you export or share the project, those required modules are all transpiled into
gigantic `app.js` file which is basically entry Javascript file. FramerFlow uses webpack
builder to generate final javascript file.


### Instant Watches
When you want to see actual value in variables during runtime, you can set up instant
watch just right beside of the variable code. During program execution when it hits your
watch point, then FramerFlow will display the value.

To setup a watch, select variable name by dragging mouse then click `Menu` > `Code` > `Add Watch`
or `ALT + W` key.

If it is object, you can click and check inside as deep as you want. This makes
your prototype's behaviours more transparent. Yes. You can use `print` statement
but this watch feature provides easiness and handful method without adding some debug
comment.

When the watch is hit several times, it records its history. You can look an way back to
some previous steps and check the value.


### REPL and Console
REPL(Read-Eval-Print-Loop) is a interactive environment to explore, test and check your
code without refreshing or building project. In order to use REPL two ways are possible.

* Select code block and then `CTRL+E` press.
* Copy code block and then paste it in Console window. Then press `Enter`.

You can directly see what the code block returns a value. You may change layer's state
or layer's property if you have a valid layer variable.

FramerFlow also provides consoel window. All console messages are redirect to the console
window. So you do not need to open up devtools for your project. If you want to check
more detail thing, you can open up devtools.

### FlowType's Static Analysis
FlowType is JavaScript static analysis tool which is created and maintained by Facebook.
Static analysis reports obvious/potential errors and invalid use cases of your variables,
statements.

Whenever you save or open one of your JavaScript files, the FlowType check automatically
starts and gives you best guides.

If the error does not generate any runtime error, you may ignore their recommendation.
But for the long term use, it may be better to adapt your code.

By adopting FlowType into FramerFlow, you can use type annotation feature. It is not
mandatory for your daily work but if you do, you can get any potential errors in eraly
stage and also get great support of intellisense feature.


## Design tools

FramerFlow provides some tools for exploration your design.

* Layer inspection
* Layer tracking
* State execution
* Change configuration

### Layer inspection
There is layer explorer button on the left-side menu. When you click, you can see the
layer hierarchy inside your editor. Each row shows some information about each corresponding
layer.

You can try several things:

#### See hierarchy, id and their name

#### Turn on/off visibility

#### Highlight the layer on preview window


### Layer Tracking
FramerFlow always tries to find which layer on runtime maps onto layer variable name in code.
By mapping runtime and static time, you can effortlessly check the layer creation and usage
in both preview and coding time.

If FramerFlow successfuly finds matched variable name, it would display in layer row.

Click on the name hops on the variable occurrences in code editor.

### States
Layer states are also displayed if it has. When you click each state, the corresponding layer
switches to the state. Whenever you want to see what effects are happened for the state, simply
click the state name.

If you click right button, there is `Execute next() state` menu. Click the menu item, then it
moves onto next state by its order.


### Change configurations
There is command menus on preview window above. You can try some configurations:

* Fullscreen mode
* Change zoom level
* Rotate left/right
* Change background color - device background or preview background
* Change device

## Publishing tools
FramerFlow helps you to easily publish a project to your clients.

* Mirroring for local publishing
* Sharing
* Export for upload on whatever you want to

### Mirroring

### Sharing
In order to share your project, you should first sign in to FramerFlow service.
Here FramerFlow only supports Facebook sign-in at the time of writing.

### Export


## Editing tools

### Multi-cursor
### Expand range
### Quick finder

## Import
There are tons of awesome, beautiful prototypes which are created and crafted by FramerJs community.
It is very nice to bring those artifacts into FramerFlow project. Therefore
FramerFlow provides importing tools. You could imports from three different ways:

* Directly import from FramerStudio project in local file
* Import from zip link
* Import from shared url

> Those projects are mainly created with CoffeeScript language. Since FramerFlow uses
> ES6 + FlowType flavored JavaScript, the transpile down to the language is critical step.
> The conversion is not perfect at the time of writing and it still has many problems.
> But hopefully most of them are easily fixed with little efforts.

Those three are all identical in terms of functionality. In this section let's
import one of beautiful project via public url.

1. First let's launch FramerFlow
2. Click File menu > Import > Import from FramerStudio share link
3. The import setting dialog is displayed. Enter the url and target folder.
4. Click import button.
5. Wait for a few minutes depending on project's size and network condition.

## Code with Javascript & ES6
Any users who are able to code with Javascript can start FrmaerJs project without learning another language. FramerFlow's main language is ES6 powered Javascript. It does fully support all of ES6 features except module feature.


# TROUBLESHOOTINGS

## Cannot find module?
## Watch setup error


# REFERENCES
Here are useful references:

* <a href="https://framerjs.com/docs/" target="_blank">FramerJS documentation</a> - Most beautiful documentation site.
* <a href="https://flowtype.org/">FlowType</a> - You can get find very useful language references


