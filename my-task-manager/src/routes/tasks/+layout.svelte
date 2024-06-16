<script lang="ts">
  import { onMount } from 'svelte';
  import { get } from 'svelte/store';
  import { userStore } from '../../stores/userStore'; // Assuming you have a userStore

  let user = get(userStore);

  onMount(() => {
    user = get(userStore); 
    console.log(user);
    if (typeof window !== 'undefined' && !user.isLoggedIn) {
      window.location.href = '/'; 
    }
  });
</script>

<nav>
  <a href="/tasks/addtask">Create Task</a>
  <a href="/tasks">User Profile</a>
  <a href="/tasks/all">Show Tasks</a>
  <a href="/tasks/category">Show Tasks By Category</a>
</nav>
<slot />

<style>
  nav {
    display: flex;
    justify-content: space-around;
    background-color: #343a40;
    padding: 10px;
  }
  nav a {
    color: #fff;
    text-decoration: none;
    padding: 5px 10px;
  }
</style>
