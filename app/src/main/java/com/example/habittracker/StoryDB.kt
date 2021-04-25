package com.example.habittracker

data class StoryDB(val storyTitle : String ?= null,
                   val storyDesc : String ?= null,
                   val usernameStory: String ?= null)