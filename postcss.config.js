module.exports = (ctx) => ({
  plugins: [
    require("autoprefixer"),
    require("tailwindcss")("tailwind.config.js"),
    require('postcss-import'),
    require('postcss-url')({ url: 'copy', useHash: true }),
    ctx.env === "production" ? require("cssnano")({ preset: "default" }) : null,
    ctx.env === "production"
      ? require("@fullhuman/postcss-purgecss")({
        content: ["resources/**/*.html", "resources/**/*.js"],
          defaultExtractor: (content) => content.match(/[\w-/:]+(?<!:)/g) || [],
        })
      : null,
  ],
});
