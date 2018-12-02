<template>
	<div id="app">
		<h1>任务列表</h1>
		<div>
			<input v-model="todo_Data.name" placeholder="name" />
			<input v-model="todo_Data.detail" placeholder="detail" />
			<button @click="submit">提交</button>
		</div>
		<ul>
			<li v-for="todo in todos" :key='todo'>
				{{todo.name}} / {{todo.detail}}
				<select v-if="todo.state=='done'" @change="chageState(todo.id,$event.target.value)">
					<option value="done" selected="selected">完成</option>
					<option value="cancel">未完成</option>
				</select>
				<select v-else @change="chageState(todo.id,$event.target.value)">
					<option value="done">完成</option>
					<option value="cancel" selected="selected">未完成</option>
				</select>
				<button @click="del(todo.id)" :id='todo.id'>删除</button>
			</li>
		</ul>
	</div>
</template>
<script>
	export default {
		name: 'app',
		data() {
			return {
				todos: [],
				todo_Data: {
					name: '',
					detail: ''
				},
				selected: []
			}
		},
		methods: {
			submit() {
				fetch('http://localhost:8080/todo', {
						method: 'post',
						headers: {
							"Content-Type": "application/json",
							"Accept": "application/json"
						},
						body: JSON.stringify(this.todo_Data)
					}).then(resp => resp.json())
					.then(data => {
						this.todos.push(this.todo_Data);
					});
			},
			del: function(id) {
				fetch('http://localhost:8080/todo/del/' + id, {
						method: 'post',
						headers: {
							"Content-Type": "application/json",
							"Accept": "application/json"
						},
						body: JSON.stringify(this.todo_Data)
					}).then(resp => resp.json())
					.then(data => {
						this.todos = data;
					});
			},
			chageState: function(id, selected) {
				fetch('http://localhost:8080/todo/' + selected + '/' + id, {
						method: 'post',
						headers: {
							"Content-Type": "application/json",
							"Accept": "application/json"
						},
						body: JSON.stringify(this.todo_Data)
					}).then(resp => resp.json())
					.then(data => {
						this.todos = data;
					}); 
			}
		},
		created() {
			fetch('http://localhost:8080/todo', {
					method: 'get'
				}).then(resp => resp.json())
				.then(data => {
					this.todos = data;

				});
		}
	}
</script>

<style>
	#app {
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
		margin-top: 60px;
	}

	.del {
		margin-left: 50px;
	}
</style>
