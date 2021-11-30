CLJ_SOURCES=$(shell find src)

dev: node_modules postcss.config.js styles.src.css $(CLJ_SOURCES)
	NODE_ENV=development yarn postcss styles.src.css -o resources/public/styles.css

# need to build once first so that purgecss can react to style strings present in advanced js build
prod: clean node_modules postcss.config.js styles.src.css resources/public/index.html $(CLJ_SOURCES)
	NODE_ENV=production yarn postcss styles.src.css -o resources/public/styles.css

node_modules:
	yarn && touch $@

clean:
	rm -rf target out resources/public/app.js resources/public/styles.css

clobber: clean
	rm -rf node_modules