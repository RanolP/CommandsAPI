# CommandsAPI
Simply Minecraft Command API

# How to use?

## Class
Creating Class, it required implements AbstractCommand
use Annotation
***
* @Aliases
* @Description
* @Usage
* @Permission
* @CommandLabel

***

## Arguments
You can use this method.
***
``` Java
addArgument(AbstractArgument)
```
***
this method is add argument worker.
***
``` Java
executeArgs(CommandSender, CommandArguments)
```
***
will run added arguments.


## Completions
AbstractCommand implements CompPlayer.
Last argument will completion with online users.
implement Completable, and complete method fill it!


## Registering
***
``` Java
CommandsAPI.registerCommand(Plugin, AbstractCommand)
```
***
API creating PluginCommand class.
setting with your annotations.
last, registering command to commandMap.
# Sorry for my bad english ;P
