<script lang="ts">
  import { deleteTask, updateTask } from '../stores/taskStore';
  import { onMount } from 'svelte';
  import { writable } from 'svelte/store';

  export let task: any;

  let editing = false;
  let updatedTask = { ...task }; // Copy of the task to edit

  function formatDate(dateStr: string) {
      const date = new Date(dateStr);
      return date.toLocaleDateString();
  }

  async function handleDeleteTask() {
      await deleteTask(task.id);
      alert('Task deleted successfully');
      window.location.href = '/tasks';
  }

  async function handleUpdateTask() {
      try {
        console.log("Debug krna hai",updatedTask)
          await updateTask(updatedTask);
          alert('Task updated successfully');
          editing = false; // Exit edit mode
          task={ ...updateTask}
      } catch (error) {
          console.error('Error updating task:', error);
          // Handle error (show message, etc.)
      }
  }

  function cancelUpdate() {
      // Reset the updatedTask and exit edit mode
      updatedTask = { ...task };
      editing = false;
  }

  // Optional: Load initial task data on mount
  onMount(() => {
      updatedTask = { ...task };
  });
</script>

<div class="task-card">
  <h3>{updatedTask.title}</h3>
  <p><strong>Category:</strong> {updatedTask.category}</p>
  <p><strong>Description:</strong> {updatedTask.description}</p>
  <p><strong>Due Date:</strong> {formatDate(updatedTask.dueDate)}</p>
  <p><strong>Status:</strong> {updatedTask.status}</p>

  {#if !editing}
      <div>
          <button on:click={() => editing = true}>Edit Task</button>
          <button on:click={handleDeleteTask}>Delete Task</button>
      </div>
  {:else}
      <form on:submit|preventDefault={handleUpdateTask}>
          <label for="title">Title:</label>
          <input type="text" id="title" bind:value={updatedTask.title} />

          <label for="category">Category:</label>
          <input type="text" id="category" bind:value={updatedTask.category} />

          <label for="description">Description:</label>
          <textarea id="description" bind:value={updatedTask.description}></textarea>

          <label for="dueDate">Due Date:</label>
          <input type="date" id="dueDate" bind:value={updatedTask.dueDate} />

          <label for="status">Status:</label>
          <select id="status" bind:value={updatedTask.status}>
              <option value="Pending">Pending</option>
              <option value="Completed">Completed</option>
              <!-- Add more status options as needed -->
          </select>

          <div>
              <button type="submit">Update Task</button>
              <button type="button" on:click={cancelUpdate}>Cancel</button>
          </div>
      </form>
  {/if}
</div>

<style>
  .task-card {
      border: 1px solid #ccc;
      padding: 20px;
      margin: 20px 0;
      border-radius: 8px;
      background-color: #f9f9f9;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      max-width: 400px;
      margin: 20px auto;
  }
  .task-card h3 {
      margin-top: 0;
      font-size: 1.5em;
      color: #333;
  }
  .task-card p {
      margin: 5px 0;
      font-size: 1em;
      color: #555;
  }
  .task-card button {
      margin-top: 10px;
      padding: 8px 16px;
      border-radius: 4px;
      cursor: pointer;
  }
  .task-card button:hover {
      opacity: 0.8;
  }
  .task-card form {
      margin-top: 10px;
  }
  .task-card form label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
  }
  .task-card form input, .task-card form textarea, .task-card form select {
      width: 100%;
      padding: 8px;
      margin-bottom: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
      font-size: 1em;
  }
</style>
