<script lang="ts">
    export let categories: string[] = [];
    export let selectedCategory: string | undefined;
    let selected: string = selectedCategory || '';
  
    const handleChange = (event: Event) => {
      const target = event.target as HTMLSelectElement;
      selected = target.value;
      // Emit event or handle category change
    };
  </script>
  
  <select on:change={handleChange} bind:value={selected}>
    <option value="">All Categories</option>
    {#each categories as category}
      <option value={category}>{category}</option>
    {/each}
  </select>
  
  <style>
    /* Add styles for the category filter */
    select {
      margin: 1em 0;
    }
  </style>
  