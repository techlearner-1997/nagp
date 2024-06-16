<script lang="ts">
  import { onMount } from 'svelte';
  import { taskStore, fetchTasks, loadingStore } from '../../../stores/taskStore';
  import { get } from 'svelte/store';

  let tasks = [];
  let jwtToken = window.sessionStorage.getItem('jwtToken');
  let userId = window.sessionStorage.getItem('userId');
  let sortBy = 'dueDate'; // Default sorting criteria
  let isLoading = false;

  onMount(() => {
    if (jwtToken && userId) {
      fetchTasks();
    }
  });
  $: tasks = get(taskStore);
  $: sortedTasks = sortTasks(tasks, sortBy);
  $: isLoading = get(loadingStore);

  
  function sortTasks(tasks, sortBy) {
    if (!tasks) return [];
    return tasks.slice().sort((a, b) => {
      switch (sortBy) {
        case 'status':
          return a.status.localeCompare(b.status);
        case 'dueDate':
          return new Date(a.dueDate) - new Date(b.dueDate);
        case 'category':
          return a.category.localeCompare(b.category);
        default:
          return 0;
      }
    });
  }
</script>

<h2>All Tasks</h2>
<div class="sort-container">
  <label for="sort">Sort by:</label>
  <select id="sort" bind:value={sortBy}>
    <option value="status">Status</option>
    <option value="dueDate">Due Date</option>
    <option value="category">Category</option>
  </select>
</div>
{#if isLoading}
  <div class="loading">Loading tasks...</div>
{:else}
  <div class="task-list">
    {#if sortedTasks.length > 0}
      {#each sortedTasks as task}
        <div class="task-item">
          <h3><a href={`/tasks/${task.id}`}>{task.title}</a></h3>
          <p>Category: {task.category}</p>
          <p>Status: {task.status}</p>
          <p>Due Date: {task.dueDate}</p>
        </div>
      {/each}
    {:else}
      <h2>No Tasks Added</h2>
    {/if}
  </div>
{/if}

<style>
  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
  }

  h2 {
    text-align: center;
    color: #333;
  }

  .sort-container {
    text-align: center;
    margin-bottom: 20px;
  }

  .sort-container label {
    margin-right: 10px;
    font-weight: bold;
  }

  .sort-container select {
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }

  .loading {
    text-align: center;
    font-size: 1.2em;
    color: #007bff;
  }

  .task-list {
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }

  .task-item {
    border: 1px solid #e0e0e0;
    padding: 15px;
    margin-bottom: 15px;
    border-radius: 8px;
    transition: background-color 0.3s, box-shadow 0.3s;
    background-color: #fafafa;
  }

  .task-item:hover {
    background-color: #f1f1f1;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }

  .task-item h3 {
    margin: 0 0 10px;
    font-size: 1.2em;
    color: #007bff;
  }

  .task-item a {
    text-decoration: none;
    color: inherit;
    transition: color 0.3s;
  }

  .task-item a:hover {
    color: #0056b3;
  }

  .task-item p {
    margin: 5px 0;
    color: #666;
  }
</style>
