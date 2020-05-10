### STAGE 1: Build ###
FROM node:slim AS build
WORKDIR /usr/src/app
COPY . .
RUN npm install
RUN npm run build

### STAGE 2: Run ###
FROM nginx:alpine
EXPOSE 80
COPY --from=build /usr/src/app/dist/wild-adventures /usr/share/nginx/html
