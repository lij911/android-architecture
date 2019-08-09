package com.lijing.dev.core.database

import org.greenrobot.greendao.annotation.Entity

@Entity
data class BasicEntity(val title: String,
                       val desc: String)