import { writable } from 'svelte/store';

// Create a writable store
export const taskStore = writable([]);
export const loadingStore = writable(false);

// Load JWT and userId from sessionStorage

const jwtToken = sessionStorage.getItem('jwtToken');
const userId =sessionStorage.getItem('userId');



export async function fetchTasks() {
    if (!jwtToken || !userId) {
        console.log(jwtToken)
        console.error('JWT token or userId not found in sessionStorage');
        return;
    }
    loadingStore.set(true);
    try {
        const response = await fetch(`http://localhost:8080/tasks/getAllTasks?userId=${userId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}};`
            }
        });

        if (response.ok) {
            const tasks = await response.json();
            taskStore.set(tasks);
            saveTasksToSessionStorage(tasks);
        } else {
            console.error('Fetch failed with status:', response.status);
        }
    } catch (error) {
        console.error('Error fetching tasks:', error);
    }
    finally {
        loadingStore.set(false);
      }
}

export async function fetchTasksByCategory(category) {
    if (!jwtToken || !userId) {
        console.error('JWT token or userId not found in sessionStorage');
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/tasks/getAllTasks?userId=${userId}&category=${category}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            }
        });

        if (response.ok) {
            const tasks = await response.json();
            return tasks
        } else {
            console.error('Fetch failed with status:', response.status);
        }
    } catch (error) {
        console.error('Error fetching tasks:', error);
    }
}

// Function to add a new task
export async function addTask(newTask) {
    if (!jwtToken) {
        console.error('JWT token or userId not found in sessionStorage');
        return;
    }
    try {
        const response = await fetch(`http://localhost:8080/tasks/addTask`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(newTask)
        });

        if (response.ok) {
            const createdTask = await response.json();
            // taskStore.update(tasks => [...tasks, createdTask]);
            // saveTasksToSessionStorage([...tasks, createdTask]);
            taskStore.update(tasks => {
                saveTasksToSessionStorage([...tasks, createdTask]);
                return [...tasks, createdTask];
            });
        } else {
            console.error('Failed to add task with status:', response.status);
        }
    } catch (error) {
        console.error('Error adding task:', error);
    }
}

// Function to update an existing task
export async function updateTask(updatedTask) {
    try {
        const response = await fetch(`http://localhost:8080/tasks/updateTask`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(updatedTask)
        });

        if (response.ok) {
            const updatedTask = await response.json();
            taskStore.update(tasks => {
                const newTasks = tasks.map(task => task.id === updatedTask.id ? updatedTask : task);
                saveTasksToSessionStorage(newTasks);
                return newTasks;
            });
        } else {
            console.error('Failed to update task with status:', response.status);
        }
    } catch (error) {
        console.error('Error adding task:', error);
    }
}

// Function to delete a task
export async function deleteTask(taskId) {
    try {
        const response = await fetch(`http://localhost:8080/tasks/deleteTask?userId=${userId}&id=${taskId}`,{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            }
        });

        if (response.ok) {
            const task = await response.json();
            taskStore.update(tasks => {
                const newTasks = tasks.filter(task => task.id !== taskId);
                saveTasksToSessionStorage(newTasks);
                return newTasks;
            });
            console.log("response:",task)
        } else {
            console.error('Failed to delete task with status:', response.status);
        }
    } catch (error) {
        console.error('Error Deleting task:', error);
    }
    
}
export async function fetchTask(taskId) {
    if (!jwtToken || !userId) {
        console.error('JWT token or userId not found in sessionStorage');
        return;
    }
    try {
        const response = await fetch(`http://localhost:8080/tasks/getTask?userId=${userId}&id=${taskId}`,{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            }
        });

        if (response.ok) {
            const task = await response.json();
            console.log("response:",task)
            return task;
        } else {
            console.error('Failed to get task with status:', response.status);
        }
    } catch (error) {
        console.error('Error fetching task:', error);
    }
}

// Function to fetch tasks from sessionStorage (called on component mount)
function loadTasksFromSessionStorage() {
    if (typeof sessionStorage !== 'undefined') {
        const savedTasks = sessionStorage.getItem('tasks');
        if (savedTasks) {
            taskStore.set(JSON.parse(savedTasks));
        }
    }
}

// Function to save tasks to sessionStorage
function saveTasksToSessionStorage(tasks) {
    if (typeof sessionStorage !== 'undefined') {
        sessionStorage.setItem('tasks', JSON.stringify(tasks));
    }
}

// Load tasks from sessionStorage when the store is initialized
loadTasksFromSessionStorage();
