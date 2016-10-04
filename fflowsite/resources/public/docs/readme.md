# QUICK START

## Introduction
FramerFlow is an editor for creating a beautiful prototype on Windows 
platform. The prototyping power is totally from the [Framer.js](http://framerjs.com)
 library.
Tons of wonderful prototyping are already created by designers, developers 
and hobbyist using Framer.js. We simply want to bring those artifacts into 
Windows platform too.

FramerFlow has fully integrated with Framer.js as much as it can. What it 
means is starting a new Framer.js project on Windows is just one click away. 
One of the good things of FramerFlow is that it uses Javascript as the main 
language. So you simply set out Framer.js project just like regular Javascript 
project. Also, the community of Javascript is huge. It is almost impossible not 
to find a feasible solution of your any technical problems.


FramerFlow is a desktop application for Windows platform. Current build only supports 64 bits OS. In this first beta release of FramerFlow, the application provides following features.

* Framer.js editor
* Built-in supports ES6 Javascript
* Layer inspection and its state manipulation
* Intellisense which is powered by [FlowType](https://flowtype.org)
* Built-in console log and REPL editor 
* Inline watch
* Share

As you get started with the beta release, be sure to check the documentation.

## Getting started
Here's how to get started with FramerFlow

### Install
Download the preview release with the link. After downloads execute it.

> *CAUTION*
> **Windows (64bits)** is required to start use the application. 
> Only Windows 10 is tested. 
>

The setup program will install FramerFlow, add the shortcut in the start menu.
It usually takes around 5~10 minutes. You will be ready to get started soon.

<img src="blank.png" class="lazy" width="472" height="312" data-src="http://resources.framerflow.com/assets/install-spinner.gif">

After the install has been successfully completed, 
FramerFlow is automatically being launched. You are able to see the screen.

<img src="blank.png" class="lazy" width="676" height="527" data-src="http://resources.framerflow.com/assets/framerflow00.png">
<i></i>

### User Interface
Here we'll go through some of the basic UI elements of FramerFlow. Your typical workspace
is just as following.

<img src="blank.png" class="lazy" width="1015" height="606" data-src="http://resources.framerflow.com/assets/userinterface_overview.gif">

* `Files` - Manage and view your project files. The toggle is possible.
* `Layers` - Understand how your layers are structured and inspect the layers.
* `Analysis` - Show any recommendation or potential risky parts of yours code
* `REPL` - You can try various thing just as you usually did in browser's console window.
* `Import` - Import assets from Photoshop. 
* `Note` - Describe a brief description of your project.
* `Share` - Share your prototype with others. Sign-in required.

### Release Notes
N/A

# USER GUIDE


## Start a new project

Starting a new project should be fairly simple.

1. Launch `FramerFlow` application.
2. Click File > New Project on the application menu.
3. Enter a project name and folder location then click 'OK'.
5. The application generates very basic project skeleton code.

<img src="blank.png" class="lazy" width="824" height="642" data-src="http://resources.framerflow.com/assets/new_project.gif">
<i></i>

### Notes on generated code

In the beta release some auto generated codes which is in `app.js` are mandatory.
Two things are most important.
1. Special comment which starts with `/* @flow */`
2. `require` auto generated configuration file

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

### ES6 - Destructuring
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



### Modules - Your First Module

Module is a great concept for building a program. If you define modules
well enough, you could build a great prototype easier and faster.
You can easily create a new module and use it
inside your program. Here is simple example.

<img src="blank.png" class="lazy" width="605" height="456" data-src="http://resources.framerflow.com/assets/codingtools_module.gif">

You can create a module file anywhere you want. But usual location is under
root folder or `modules` folder. If you use module creation ui, the file is
 creaed under `modules` folder.

 #### `require`
 In order to use module you should use `require` function.

```
// Bring your module into current file
// For the local module, the path is relative from current project.
//
let m1 = require("./relative_file_name");

// Node module
// Directly use the module name itself.
let _ = require("lodash");
```
The variables or functions from modules are only exposed to outside
when they are set in `module.exports`. The detail and complete guide is here:

* [node.js module documentation](https://nodejs.org/api/modules.html#modules_module_exports)
* [FlowType module](https://flowtype.org/docs/modules.html) - FramerFlow *only* supports __commonjs__ module system.

### Modules - Node.js
FramerFlow basically supports node.js module system. Let's start to use `lodash`
module which is a great utility library for handling array, object and etc.
See the image.

<img src="blank.png" class="lazy" width="720" height="512" data-src="http://resources.framerflow.com/assets/codingtools_modules_node.gif">

It's simple, isn't it? You can freely use nodejs modules.

> Theses module files are merged into one single file, `app.js`.
> The packaging process is done via [WebPack](https://webpack.github.io/).


### Instant watch
Instant watch is a handy tool to inspect value of variable. You can easily set up
watch by using keyboard shortcut, `Alt + w`.

If the value of watch is object, you can deep dive into object properties. The watch
only report a value to you whenever it gots a hit from code execution.

Here is the guide.

<img src="blank.png" class="lazy" width="720" height="489" data-src="http://resources.framerflow.com/assets/codingtools_instant_watch.gif">

#### Watch History
If a watch got several hits such as using `setInterval`, it records its history.
So until next refresh you can have better understand when you need to see what is going
on inside.


### REPL and Console
REPL(Read-Eval-Print-Loop) is an interactive tool to explore your code easily. Basic steps
for REPL is simple.

1. Select code block
2. Then `Ctrl + e`
3. The code exeuction result is displayed in console window.

<img src="blank.png" class="lazy" width="720" height="535" data-src="http://resources.framerflow.com/assets/codingtools_repl.gif">


FramerFlow also provides console window. All console log (i.e., `console.log("hello");`) are 
redirect to the console window. So you do not need to open up devtools for your project. 
If you want to inspect more deeply, you can open Chrome's devtool.

<img src="blank.png" class="lazy" width="720" height="535" data-src="http://resources.framerflow.com/assets/codingtools_console.gif">
<i></i>
 

### Code Analysis
Code analysis is one of the wonderful tools where FramerFlow offers to you. 
Generally we call the analysis as static analysis. Most benefits from the analysis
 is that you get __a good coding private teacher__ by telling you what you should do and shouldn't.
 You can run your prototype you have static analysis errors or not. It does not matter
 to run your prototype since the analysis is statically from parsing your source code
 not running it. This is perfectly ok for short term project. But when you consider more 
 complicated and bigger project, it could be better to follow the guide and fix the errors
 from static analysis.
 
 > The analysis is from Facebook's [FlowType](https://flowtype.org/). You can check the
 > version of `flow.exe` of which FramerFlow usees in about dialog.

#### Example 1 - Type checking
The function `square` accepts one number and returns its square value. The example shows
when invalid input is given, the analysis suggests potential error point.

<img src="blank.png" class="lazy" width="720" height="535" data-src="http://resources.framerflow.com/assets/codingtools_ana_type_tracking.gif">

#### Example 2 - Null checking 
FlowType is really good at finding possible null point. The sample is here.
`maybeNo` could be null in certain situation. So FlowType detects the possibility in the code.

<img src="blank.png" class="lazy" width="720" height="535" data-src="http://resources.framerflow.com/assets/codingtools_ana_fixing_errors.gif">





## Design tools

FramerFlow provides interesting tools to test and inspect your design.

* Layer inspection
* Layer tracking
* State execution
* Change configuration

### Layer inspection
There is layer explorer button on the left-side menu. When you click, you can see the
layer hierarchy inside your editor. You can try very basic layer's properties.
 
 * Visibility on/off
 * Inspect in preview
 * States

<img src="blank.png" class="lazy" width="720" height="453" data-src="http://resources.framerflow.com/assets/designtools_layer_inspection.gif">


### Jump to Layer Definition
 FramerFlow always tries to find an way to map your source code's variable to
 run time layer. The mapping opens huge opportunities where design asset can be
 seamlessly integrated into code and vice versa. For example, FramerStudio's autocode
 could be one of the great examples.

 Current beta release only provides jump to declaration feature. When FramerFlow found
 mapping variable, the name is on the layer panel. If you click the variable, you can
 see where the layer is actually used in your design.

<img src="blank.png" class="lazy" width="720" height="536" data-src="http://resources.framerflow.com/assets/designtools_layer_jump.gif">


### States
Layer states are also displayed if it has. When you click each state, the corresponding layer
switches to the state. Whenever you want to see what effects are happened for the state, simply
click the state name.

If you click right button, there is `Execute next() state` menu. Click the menu item, then it
moves onto next state by its order.


<img src="blank.png" class="lazy" width="720" height="536" data-src="http://resources.framerflow.com/assets/designtools_layer_states.gif">

### Change configurations
There is command menus on preview window above. You can try some configurations:

* <i class="material-icons">refresh</i> Refresh preview
* <i class="material-icons">fullscreen</i> Fullscreen mode
* <i class="material-icons">rotate_left</i> <i class="material-icons">rotate_right</i> Rotate left/right
* <i class="material-icons">zoom_in</i> Change zoom level
* <i class="material-icons">colorize</i> Change background color - device background or preview background
* <i class="material-icons">devices_other</i> Change device

> One of those setting changes let the application to generate revised `framer.cfg.js` file.

## Publishing tools
FramerFlow helps you to easily publish a project to your clients.

* Mirroring for local publishing
* Sharing
* Export for upload on whatever you want to

### Mirroring
FramerFlow provides mirroring option. You can easily check the prototype in your mobile
device's browser if the device uses same WiFi router.

On the mirroring page mouse, touch and scroll actions are propagates into every connected
 devices.

<img src="blank.png" class="lazy" width="720" height="536" data-src="http://resources.framerflow.com/assets/publishing_mirror.gif">

### Sharing
In order to share your project, you should first sign in to FramerFlow service.
FramerFlow only supports Facebook login. Just login with your Facebook credential.

Once you sign in share is just one-tap away. Click `share` button on the left, bottom side menu.
Then click `Publish`. That's it. You will get the link. You can share freely as you want.
If you do not like share anymore, then just go back to share dialog. Then `Delete` button.

<img src="blank.png" class="lazy" width="720" height="536" data-src="http://resources.framerflow.com/assets/publishing_share.gif">

### Export
When you need to host your prototype privately or back up your prototype, `export` could be
helpful in the situation. Click `File` > `Export` then type in the zip file name. Pretty simple.

<img src="blank.png" class="lazy" width="720" height="592" data-src="http://resources.framerflow.com/assets/publishing_export.gif">

## Editing tools
FramerFlow provides some modern editing features as following:

* Multi-cursor
* Expand range
* Quick finder

### Multi-cursor
First select some region and `Ctrl + d` will select next same occurrence. You can simultaneously
change with multiple selections.

<img src="blank.png" class="lazy" width="720" height="536" data-src="http://resources.framerflow.com/assets/editing_multi_cursor.gif">

### Expand range
By pressing `Ctrl + w` you can grow the selection into next block level. Very helpful tool once
you get used.

<img src="blank.png" class="lazy" width="720" height="536" data-src="http://resources.framerflow.com/assets/editing_expand_region.gif">

### Quick finder
`Alt + o` opens quick finder. By a few keystroke you can find your file easily.

<img src="blank.png" class="lazy" width="720" height="536" data-src="http://resources.framerflow.com/assets/editing_quick_finder.gif">

### Auto format
`Ctrl + '` automatically reformat you code. Do not spend you time to beautify your code. It is
editor's job.

<img src="blank.png" class="lazy" width="720" height="536" data-src="http://resources.framerflow.com/assets/editing_auto_format.gif">

## Import Tools
### FramerStudio
FramerStudio is a pretty awesome tool for creating prototype. There are tons of projects which
are beautifully created and carefully crafted by Framer.js community. All most of all of them was
 created by FramerStudio. Bringing those beautiful artifacts into FramerFlow is really attractive.
 Therefore FramerFlow provides some importing tools. There are three ways to import the project.

* Directly import from FramerStudio project in local file
* Import from FramerStuido's zip link
* Import from FramerStudio's shared url

> Those projects are mainly created with CoffeeScript. Since FramerFlow uses
> JavaScript, FramerFlow need to an way to convert from CoffeeScript into ES6 Javascript.
> From CoffeeScript to ES6 Javascript is easy but to ES6 Javascript is a
> different story. In the preview release there are many problems for automatic
> transfer. We'll fix it in coming releases.

### Import from FramerStuio URL
You can import a project from shared URL into FramerFlow.

<img src="blank.png" class="lazy" width="720" height="591" data-src="http://resources.framerflow.com/assets/import_url.gif">

# REFERENCES
Here are useful references:

* <a href="https://framerjs.com/docs/" target="_blank">Framer.js documentation</a> - Most beautiful documentation site.
* <a href="https://flowtype.org/" target="_blank">FlowType</a> - You can get find very useful language references
* <a href="https://https://developer.mozilla.org/en-US/docs/Web/JavaScript/New_in_JavaScript/ECMAScript_6_support_in_Mozilla/" target="_blank">ES6 in Mozilla</a> - Great references for ES6

<div style="min-height: 25vh"></div>


