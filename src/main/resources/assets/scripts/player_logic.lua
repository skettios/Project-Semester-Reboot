local rotation = 0

function update(entity, deltaTime)
    if Gdx.input:isKeyPressed(Keys.LEFT) then
        entity.transform:move(-200 * deltaTime, 0)
    elseif Gdx.input:isKeyPressed(Keys.RIGHT) then
        entity.transform:move(200 * deltaTime, 0)
    end

    if Gdx.input:isKeyPressed(Keys.UP) then
        entity.transform:move(0, 200 * deltaTime)
    elseif Gdx.input:isKeyPressed(Keys.DOWN) then
        entity.transform:move(0, -200 * deltaTime)
    end
end