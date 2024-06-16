<script lang="ts">
  import { onMount } from 'svelte';
    import { fetchTasksByCategory,fetchTasks, taskStore } from '../../../../stores/taskStore';
    import { get } from 'svelte/store';
    import { page } from '$app/stores';
    
    let tasks = [];
    let category=$page.params.category
    let jwtToken = sessionStorage.getItem('jwtToken');
    let userId = sessionStorage.getItem('userId');

    $: tasks = get(taskStore);

    onMount(() => {
        if (jwtToken && userId) {
            fetchTasks();
        }
    });

    $: tasks = tasks.filter(x => x.category === category);
  </script>
  
  <h2>All Tasks</h2>
  <div class="task-list">
    {#if tasks.length >0}
    {#each tasks as task}
      <div class="task-item">
        <h3><a href={`/tasks/${task.id}`}>{task.title}</a></h3>
        <p>Category: {task.category}</p>
      </div>
    {/each}
    {:else}
    <h2>No Tasks Added</h2>
    {/if}
  </div>
  
  <style>
    .task-list {
      padding: 20px;
    }
    .task-item {
      border: 1px solid #ccc;
      padding: 10px;
      margin-bottom: 10px;
      border-radius: 4px;
    }
    .task-item h3 {
      margin: 0;
    }
    .task-item a {
      text-decoration: none;
      color: #007bff;
    }
    .task-item a:hover {
      text-decoration: underline;
    }
  </style>
  