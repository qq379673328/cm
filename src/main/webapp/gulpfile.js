var gulp = require('gulp');
var jshint = require('gulp-jshint');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var clean = require('gulp-clean');

// 语法检查
gulp.task('jshint', function () {
    return gulp.src('static/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

//js 合并压缩
gulp.task('minify', function (){
     return gulp.src([
                      'static/app/**/*.js',
                      '!static/app/components/3rd/**/*.js',
                      '!static/app/bower_components/**/*.js',
                      '!static/app/appformlist.js'
    		 ])
        .pipe(concat('main.js'))
        .pipe(gulp.dest('static/build'))
        .pipe(uglify())
        .pipe(rename('main.min.js'))
        .pipe(gulp.dest('static/build'));
});

//清空发布
gulp.task('clean', function () {
	gulp.src(['static/build/**'], {read: false}).pipe(clean());
});

// 监视文件的变化
gulp.task('watch', function () {
    gulp.watch(['static/**/*.js'],['minify']);
});

// 注册缺省任务
gulp.task('default', ['minify', 'watch']);