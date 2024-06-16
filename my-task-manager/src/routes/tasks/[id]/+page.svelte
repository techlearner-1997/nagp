<script lang="ts">
    import { page } from '$app/stores';
    import TaskCard from '../../../components/TaskCard.svelte';
    import {fetchTask } from '../../../stores/taskStore';

  
  
    let task:any = null;
      fetchTaskById($page.params.id);
  
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
  