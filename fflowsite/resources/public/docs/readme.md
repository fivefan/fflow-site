# QUICK START

## Introduction
FramerFlow is an new editor that allows you to create a beautiful prototype
faster and easier than ever before. The prototype is powered by awesome
[Framer.js](http://framerjs.com). It uses vanilla Javascript as main 
language so you may start a new project right away if you already know Javascript.

FramerFlow is a desktop application for Windows platform. Current build only
supports 64 bits OS. In this first preview release of FramerFlow, the application
provides:

* Framer.js fully integrated editor
* Built-in supports regular ES6 Javascript including modules.
* Layer inspection / state manipulation
* Intellisense which is powered by [FlowType](https://flowtype.org)
* Built-in console log/ REPL editor 
* Inline watch
* Share
* Etc.

As you get started with the preview release, be sure to check the documentation.


## Getting started
Here's how to get started with FramerFlow

### Install
Download the preview release with the link. After downloads execute it.


> *CAUTION*
> **Windows platform (64bits)** is required to start use the application. 
>

[DOWNLOAD SETUP >](https://release.framerflow.com/download)

You will see the install processing window. It usually takes around 5~10 minutes
to finish it. You will be ready to get started soon.

<img src="blank.png" class="lazy" width="472" height="312" data-src="http://resources.framerflow.com/assets/install-spinner.gif">

After install successfully done FramerFlow is automatically being launched.
You are able to see the screen.

<img src="blank.png" class="lazy" width="676" height="527" data-src="http://resources.framerflow.com/assets/framerflow00.png">
<i></i>

### User Interface
Here we'll go through some of the basic UI elements of FramerFlow. Your typical workspace
is just as following.

<img src="blank.png" class="lazy" width="1015" height="606" data-src="http://resources.framerflow.com/assets/userinterface_overview.gif">

* `Files` - manage and see your project files. Toggle is possible.
* `Layers` - Understand how your layers are structured and inspect.
* `Analysis` - Show any recommendation or potential risky parts of your code
* `REPL` - You can try various thing just as you did in usual browser's console window
* `Import` - Import assets from Photoshop. Only photoshop is possible.
* `Note` - Describe brief description about your project.
* `Share` - Share your prototype with other people. Sign-in required.

### Release Notes
N/A

# USER GUIDE


## Start a new project

Starting a new project is simple and easy.

1. Launch FramerFlow appliaction.
2. Click File > New Project on the application menu.
3. Enter project name and location then click OK.
5. You can see very basic skeleton project code.

<img src="blank.png" class="lazy" width="824" height="642" data-src="http://resources.framerflow.com/assets/new_project.gif">
<i></i>

### Notes on generated code

In the preview release some codes are mandatory and special to FramerFlow.
The code starts with special comment.

```javascript
/* @flow */
```

This strange comment is for FlowType which is core part of intellisense and
code analysis in FramerFlow. In other words when you need some tooling
don't forget to put the special hint at the very top of the each file. 
Same approach is also applied to __module__ creation.


```javascript
require("./framerflow.cfg");
```

`require` is a function to import module (_FramerFlow fully supports node.js module system_).
Here the application tries to import auto generated module file `framerflow.cfg`. 
The file includes to set up basic environment 
such as device type, zoom factor, rotation and background, etc.

> Whenever you change device or background via FramerFlow UI, the file is
> automatically generated and updated.



## Coding tools
FramerFlow has decent coding features to help you better.

* ES6 with type annotation
* Modules
* Instant Watches
* REPL and Console
* Flowtype's Static Analysis

### ES6 - Overview
ES6 is next version of JavaScript. Its expressive power is same with CoffeeScript
in terms of simplicity, beauty. By adopting ES6 you can keep similar things
when you do CoffeeScript codings.

### ES6 - Fat Arrow
`()=>{}`. Fat arrow is very shorthand form to define inline function or anonymous function.

<img src="blank.png" class="lazy" width="607" height="508" data-src="http://resources.framerflow.com/assets/es6_fat_arrow.gif">


Please check the code.
```
// Fat arrow.
var f1 = ()=> {
    console.log(`I'm f1:${Date.now()}`);
    return true;
};
f1();
```


### ES6 - Scoped variable
`let` finally takes care of very bad thing of Javascript.
Following code is a very subtle problem. It is a source of many bugs.

```
var a = "San Francisco";
{
    var a = "Seattle";
    console.log(a);
}
console.log(a);
```
The console log always prints `Seattle`. 

You can easily fix the problem by changing `var` into `let`: 
Check this.

<img src="blank.png" class="lazy" width="607" height="508" data-src="http://resources.framerflow.com/assets/es6_let.gif">
<i></i>

### ES6 - Contant
`const` provides read-only variable. Once you initialize value, you have 
no right to change the value. 
> Try program with `const` as many as you can,
> your code would be much better in terms of simplicity and robusteness.
> This is definitely an way to be awesome programmer. 

<img src="blank.png" class="lazy" width="607" height="508" data-src="http://resources.framerflow.com/assets/es6_const.gif">

``` 
const IsFramerJsAwesome = true;
IsFramerJsAwesome = false;
```

### ES6 - Destructuring and Template Strings
`Destructuring` is a powerful concept. You can break thing into many small parts.
This is time saver. `Template Strings` is a really handy tools for formatting
strings.

<img src="blank.png" class="lazy" width="607" height="508" data-src="http://resources.framerflow.com/assets/es6_destructuring.gif">

```
let cityInfo = {
    country: "USA",
    capital: "DC"
};

let {country, capital} = cityInfo;

console.log(`${capital} is the capital of ${country}`)
```

Template string starts with backtick \` and is closed with backtick\`



### Modules

Module is a great concept for building a program. If you design well, your modules
can be used to place altogether. You can easily create a new module and use it
inside your program. Here is simple example.

<img src="blank.png" class="lazy" width="605" height="456" data-src="http://resources.framerflow.com/assets/codingtools_module.gif">
<i></i>


### Modules - Node.js 
FramerFlow basically supports node.js module system. `require` is used to import module file
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


