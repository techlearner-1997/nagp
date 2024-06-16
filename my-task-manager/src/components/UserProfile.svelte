<script>
  export let user;
</script>
<div class="user-profile">
  
  <h2>{user.name}</h2>
  <p>Phone: {user.phone}</p>
  <p>Email: {user.email}</p>
</div>

<style>
  .user-profile {
    border: 1px solid #ccc;
    padding: 20px;
    margin: 20px 0;
    border-radius: 8px;
    background-color: #f9f9f9;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    max-width: 400px;
    margin: 20px auto;
  }
  .user-profile h2 {
    margin-top: 0;
    font-size: 1.5em;
    color: #333;
  }
  .user-profile p {
    margin: 5px 0;
    font-size: 1em;
    color: #555;
  }
</style>
