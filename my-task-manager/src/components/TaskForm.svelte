<script lang="ts">
  import { createEventDispatcher } from 'svelte';
  import { addTask } from '../stores/taskStore';

  export let task = { title: '', description: '', category: '', dueDate: '', status: 'Pending' };

  let title = task.title;
  let description = task.description;
  let category = task.category;
  let dueDate = task.dueDate;
  let status = task.status;
  let userId = sessionStorage.getItem('userId');

  const dispatch = createEventDispatcher();

  async function submitForm() {
    let newTask={ title: title, description: description, category: category, dueDate: dueDate, status: status,userId:userId }
          await addTask(newTask);
          alert('Task Created Successfully');
      }

      // Reset form fields after task creation or update
      title = '';
      category = '';
      description = '';
      dueDate = '';
      status = 'Pending';

</script>

<div class="container">
  <h1>Create Task</h1>
  <section id="create-task">
    <form on:submit|preventDefault={submitForm}>
      <div class="form-group">
        <label for="task-title">Title</label>
        <input type="text" id="task-title" bind:value={title} required>
      </div>
      <div class="form-group">
        <label for="task-category">Category</label>
        <select id="task-category" bind:value={category} required>
          <option value="work">Work</option>
          <option value="personal">Personal</option>
          <option value="urgent">Urgent</option>
        </select>
      </div>
      <div class="form-group">
        <label for="task-desc">Description</label>
        <textarea id="task-desc" bind:value={description} required></textarea>
      </div>
      <div class="form-group">
        <label for="task-due-date">Due Date</label>
        <input type="date" id="task-due-date" bind:value={dueDate} required />
      </div>
      <div class="form-group">
        <button type="submit">Create Task</button>
      </div>
    </form>
  </section>
</div>

<style>
  .container {
    padding: 20px;
  }
  form {
    display: flex;
    flex-direction: column;
    max-width: 600px;
    margin: auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  .form-group {
    margin-bottom: 15px;
  }
  .form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
  }
  .form-group input,
  .form-group select,
  .form-group textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }
  .form-group button {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  .form-group button:hover {
    background-color: #0056b3;
  }
</style>
