<script lang="ts">
    import { onMount } from 'svelte';
    import { page } from '$app/stores';
    import TaskCard from '../../../components/TaskCard.svelte';
    import {fetchTask } from '../../../stores/taskStore';
    import { get } from 'svelte/store';
  
  
    let task:any = null;
    // $: {
    //   $page, 
      fetchTaskById($page.params.id);
    // }
  
    async function fetchTaskById(id: string) {
       console.log(task) 
      task=await fetchTask(id);
      console.log('After setting',task)
    }
  </script>
  
  {#if task}
    <TaskCard {task} />
  {:else}
    <p>Task not found</p>
  {/if}
  