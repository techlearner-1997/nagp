<script lang="ts">
</script>

<h1>Select a Task Category</h1>
<div class="category-buttons">
        <a href="/tasks/category/personal" class="category-button">Personal</a>
        <a href="/tasks/category/work" class="category-button">Work</a>
        <a href="/tasks/category/urgent" class="category-button">Urgent</a>
    </div>

    <style>
        
        .category-buttons {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .category-button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            background-color: #10b798;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .category-button:hover {
            background-color: #096453;
        }
    </style>