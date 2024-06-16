<script lang="ts">
  import { onMount } from 'svelte';
  import UserProfile from '../../components/UserProfile.svelte';
  import { get } from 'svelte/store';
  import { userStore, setUser } from '../../stores/userStore';

  $: user = get(userStore);

onMount(() => {
    user = get(userStore);  // Initialize user from store
});

</script>

<section>
<UserProfile {user}/>
</section>

<style>
section {
  padding: 20px;
}
</style>
