const coursesNames = {
    fundamentals: "Java Fundamentals",
    advanced: "Java Advanced",
    db: "Java DB",
    web: "Java Web",
    htmlAndCss: "HTML & CSS",
};

const educationFormNames = {
    online: "Online",
    onSite: "On Site",
};

const availableCourses = [
    { name: coursesNames.fundamentals, price: 170 },
    { name: coursesNames.advanced, price: 180 },
    { name: coursesNames.db, price: 190 },
    { name: coursesNames.web, price: 490 },
];

const educationForms = [
    { name: educationFormNames.onSite, discount: 0},
    { name: educationFormNames.online, discount: 0.06},
];

getCourseItem = (course) => {
    return $("<label/>")
        .append(
            $("<input/>")
                .attr("type", "checkbox")
                .val(course.name)
        )
        .append(course.name)
};

const getEducationFormItem = (educationForm) => {
    return $("<label/>")
        .append(educationForm.name)
        .append(
            $("<input/>")
                .attr("type", "radio")
                .attr("name", "education-form")
                .val(educationForm.name)
        )
};

//BEFORE OPTIMISATION
// const generateCoursesList = () => {
//   courses
//       .map(course => getCourseItem(course))
//       .map(courseItem => {
//           return $("<li/>").append(courseItem)
//       })
//       .forEach(item => item.appendTo("#list-courses"))
// };/

//AFTER OPTIMISATION
const generateList = (items, generateItemFunc) =>
    items
        .map(item => generateItemFunc(item))
        .map(itemElement => $("<li/>").append(itemElement));

const generateAvailableCoursesList = () => {
    const courseItems = generateList(availableCourses, getCourseItem);
    courseItems.forEach(item => item.appendTo("#list-courses"));
};

const generateEducationFormsList = () => {
    const educationFormsItems = generateList(educationForms, getEducationFormItem);
    educationFormsItems.forEach(item => item.appendTo("#list-education-forms"));
    $("#list-education-forms li:first-of-type input")
        .attr("checked", true);
};

const getSelectedCourses = () => {
    const courseNames = Array.from($("#list-courses input:checked"))
        .map(input => $(input).val());

    return courseNames
        .map(courseName =>
            // !!! DESTRUCTURING -> Clones an object {...object} !!!
            ({...availableCourses.find(course => course.name === courseName)}))
};

const getSelectedEducationForm = () => {
    const educationFormName = $("#list-education-forms input:checked").val();
    return educationForms
        .find(educationForm => educationForm.name === educationFormName);
};

const getMyCourseItem = (course) => course.name;

const generateMyCoursesList = (courses) => {
    const courseItems = generateList(courses, getMyCourseItem);

    // Clear My Courses Selection and Avoid multiple selection overlap
    $("#list-my-courses").html("");
    courseItems.forEach(item => item.appendTo("#list-my-courses"));
};

//HAS COURSE METHOD Transformed below >> to getCourse()
// const hasCourse = (courses, courseName) =>
//     courses.some(course => course.name === courseName);

const getCourse = (courses, courseName) =>
    courses.find(course => course.name === courseName);

const decorateCourses = (courses) => {
    const fundamentalsCourse = getCourse(courses, coursesNames.fundamentals);
    const advancedCourse = getCourse(courses, coursesNames.advanced);
    const dbCourse = getCourse(courses, coursesNames.db);
    const webCourse = getCourse(courses, coursesNames.web);

    if (fundamentalsCourse && advancedCourse) {
        // discount 10%
        advancedCourse.price *= 0.9;

        if (dbCourse) {
            // discount 6%
            fundamentalsCourse.price *= 0.94;
            advancedCourse.price *= 0.94;
            dbCourse.price *= 0.94;

            if (webCourse) {
                // Add HTML & CSS
                courses.push(
                    { name: coursesNames.htmlAndCss, price: 0}
                );
            }
        }
    }
};

const onSignMeUpClick = () => {
    const courses = getSelectedCourses();
    const educationForm = getSelectedEducationForm();
    decorateCourses(courses);

    let totalPrice = courses.reduce((sum, course) => sum + course.price, 0);
    if (educationForm.name === educationFormNames.online) {
        totalPrice *= 0.94;
    }
    $("#total-price").html(Math.round(totalPrice) + ".00");
    generateMyCoursesList(courses);
};

$(function () {
    generateAvailableCoursesList();
    generateEducationFormsList();

    $("#btn-sign-me-up")
        .on("click", onSignMeUpClick);
});
