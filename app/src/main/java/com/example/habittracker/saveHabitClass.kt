package com.example.habittracker

import java.sql.Time
import java.time.LocalTime

class saveHabitClass (val habitID : String,
                      val habitTitle : String,
                      val habitDesc : String,
                      val dur3month : Boolean,
                      val dur6month : Boolean,
                      val dur12month : Boolean,
                      val morningTime : String,
                      val eveningTime : String)