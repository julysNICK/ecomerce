postgres:
	docker run --name javaeccommerce -e POSTGRES_USER=root -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres


createdb:
	docker exec -it javaeccommerce createdb --username=root --owner=root simple_ecommerce

dropdb:
	docker exec -it javaeccommerce dropdb simple_ecommerce
	