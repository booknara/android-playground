/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.data

import com.example.android.architecture.blueprints.todoapp.data.source.local.FakeTaskDao
import com.example.android.architecture.blueprints.todoapp.data.source.local.LocalTask
import com.example.android.architecture.blueprints.todoapp.data.source.local.toExternal
import com.example.android.architecture.blueprints.todoapp.data.source.network.NetworkTask
import com.example.android.architecture.blueprints.todoapp.data.source.network.TaskNetworkDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.network.toLocal
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultTaskRepositoryTest {
    private val localTasks = listOf(
            LocalTask("1", "title1", "description1", false),
            LocalTask("2", "title2", "description2", true)
    )
    private val localDataSource = FakeTaskDao(localTasks)
    private val networkDataSource = TaskNetworkDataSource()
    private val dispatcher = UnconfinedTestDispatcher()
    private val scope = TestScope(dispatcher)
    
    private val taskRepository = DefaultTaskRepository(
            localDataSource = localDataSource,
            networkDataSource = networkDataSource,
            dispatcher = dispatcher,
            scope = scope
    )
    @Test
    fun observeAll_exposesLocalData() = runTest { 
        val tasks = taskRepository.observerAll().first()
        assertEquals(localTasks.toExternal(), tasks)
    }
    @Test
    fun onTaskCreation_localAndNetworkAreUpdated() = scope.runTest { 
        val newTaskId = taskRepository.create(
                localTasks[0].title,
                localTasks[0].description
        )
        val localTasks = localDataSource.observeAll().first()
        assertEquals(true, localTasks.map { it.id }.contains(newTaskId))
        
        val networkTasks = networkDataSource.loadTask()
        assertEquals(true, networkTasks.map { it.id }.contains(newTaskId))
    }

    @Test
    fun onTaskCompletion_localAndNetworkAreUpdate() = scope.runTest {
        taskRepository.complete("1")
        val localTasks = localDataSource.observeAll().first()
        val isLocalTaskComplete = localTasks.firstOrNull { it.id == "1" } ?.isCompleted
        assertEquals(true, isLocalTaskComplete)
        
        val networkTasks = networkDataSource.loadTask()
        val isNetworkTaskComplete = networkTasks.first { it.id == "1" } ?.status == NetworkTask.TaskStatus.COMPLETE
        assertEquals(true, isNetworkTaskComplete)
    }
    
    @Test
    fun onRefresh_localIsEqualToNetwork() = runTest { 
        val networkTasks = listOf(
                NetworkTask("3", "title3", "desc3"),
                NetworkTask("4", "title4", "desc4")
        )
        networkDataSource.saveTask(networkTasks)
        taskRepository.refresh()
        assertEquals(networkTasks.toLocal(), localDataSource.observeAll().first())
    }
    
}
