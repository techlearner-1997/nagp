<script lang="ts">
  import { onMount } from 'svelte';
    import { taskStore,fetchTasks } from '../../../stores/taskStore';
    import { get } from 'svelte/store';

    let tasks = [];
    let jwtToken = window.sessionStorage.getItem('jwtToken');
    let userId = window.sessionStorage.getItem('userId');

    $: tasks = get(taskStore);

    onMount(() => {
        if (jwtToken && userId) {
            fetchTasks();
        }
    });
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
  