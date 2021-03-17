$(document).ready(function () {
    $("#playlist > tbody").append(
        '<tr style="background-color: teal" class="teal"><td colspan="3"><span data-toggle="modal" data-target="#add-playlist" style="color: white" title="Create Playlist" class="fa fa-plus fa-3x" aria-hidden="true"> </span></td></tr>'
    );

    function getId(obj, prefix){
        return $(obj)
            .attr("class")
            .match(prefix + "-[0-9]+")[0]
            .split("-")[1]
            .trim();
    }

    $(".remove-song").on("click", function (evt){
       var result = confirm("Sure you want to delete?");
       if(!result){
           return;
       }
       let songId = getId(this, "song");
       let playlistId = getId(this, "playlist");
       $.ajax({
          url:`playlist/${playlistId}/songs/${songId}`,
          type: "DELETE", success: function (data){
              alert("successfully deleted the song from playlist");
              window.location.reload();
           },
       });

    });


    $(".delete_playlist").on("click", function (evt){
        var result = confirm("Sure you want to delete?");
        if(!result){
            return;
        }
        let playlistId = getId(this, "playlist");
        $.ajax({
            url:`playlist/${playlistId}`,
            type: "DELETE", success: function (data){
                alert("successfully deleted the playlist");
                window.location.reload();
            },
        });
    });

    $("form.add-playlist").on("submit", function (evt){
       evt.preventDefault();
       let name = $("#playlist-name").val().trim();

       if(name==null || name.length == 0 || name.length < 3){
           alert("name cannot be null/empty/length less than 3");
           return;
       }

        $.ajax({
            url:`playlist/${name}`,
            type: "POST", success: function (data){
                alert("successfully created the playlist");
                window.location.reload();
            },
            complete: function (){},
        });
    });

    $("span[data-id]").on("click", function (evt){
       $("#playlist-id").val($(this).attr("data-id"));
    });

    $("form.add-song").on("submit", function (evt){
        evt.preventDefault();
        let name = $("#song-name").val().trim();
        if (name == null || name.length == 0 || name.length < 3) {
            alert("name cannot be null/empty/length less than 3");
            return;
        }
        let url = $("#cover-url").val().trim();
        let match = url.match("http.*(jpg|jpeg|gif|png)");
        if (match == null || match < 0) {
            alert("enter valid image url");
            return;
        }
        let playlistId = $("#playlist-id").val().split("-")[1].trim();


        $.ajax({
            url:`playlist/${playlistId}/add`,
            dataType: "json",
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify({
               name: name,
               cover_url: url,
            }),
            success: function (data){
                alert("successfully added song to playlist");
                window.location.reload();
            },
            complete: function (){
                $("#playlist-id").val("");
            },
        });
    });
});