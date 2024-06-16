<script lang="ts">
    import TaskForm from '../../../components/TaskForm.svelte';
    import { page } from '$app/stores';
    console.log(page.params)
    </script>
    <TaskForm />