# 

<p align="center">
    <a href="https://jonathanjaramorales.herokuapp.com">
        <img src="https://github.com/JJaraM/blog-microservice-ui/blob/master/src/main/resources/public/logo-210x.png" height="210">
    </a>
</p>

<p align="center">
    <h2 align="center">
        <a href="https://jonathanjaramorales.herokuapp.com">Gradle System Variable</a>
    </h2>
    <p align="center">
        <a href="https://jonathanjaramorales.herokuapp.com/category/184"><img src="https://img.shields.io/badge/-gradle-fd6d75.svg"/></a>
        <a href="https://jonathanjaramorales.herokuapp.com/category/178"><img src="https://img.shields.io/badge/-spring%5Fboot-fd6d75.svg"/></a>
    </p>
</p>


# Prerequisites:
* Have installed gradle or use an IDE that run gradle
* 5 minutes

# Updating the gradle.build

In your gradle.build file you need to add the following instruction, in the task that you want, for this case we are going to use the bootRun
```gradle
bootRun {
    systemProperty 'spring.profiles.active', 'jjara'
}
```
You can visit the repository in this [link](https://github.com/JJaraM/tech-learning/tree/prod/gradle-system-variable), or you can run the following commands on your computer.
```gitclone
git clone https://github.com/JJaraM/tech-learning.git

git checkout gradle-system-variable
```

